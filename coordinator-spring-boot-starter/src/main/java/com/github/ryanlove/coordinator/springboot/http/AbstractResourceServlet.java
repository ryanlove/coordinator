package com.github.ryanlove.coordinator.springboot.http;

import com.github.ryanlove.coordinator.springboot.http.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ryan
 */
public abstract class AbstractResourceServlet extends HttpServlet {

    private final ResourceHandler handler;

    public AbstractResourceServlet(String resourcePath){
        handler = new ResourceHandler(resourcePath);
    }

    protected String getFilePath(String fileName) {
        return handler.resourcePath + fileName;
    }

    protected void returnResourceFile(String fileName, String uri, HttpServletResponse response)
            throws ServletException,
            IOException {
        handler.returnResourceFile(fileName, uri, response);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        handler.service(request, response, servletPath, AbstractResourceServlet.this::process);
    }

    abstract String process(String url);

    public interface ProcessCallback {
        String process(String url);
    }

    public static class ResourceHandler {

        protected String resourcePath;

        public ResourceHandler(String resourcePath) {
            this.resourcePath = resourcePath;
        }

        protected void returnResourceFile(String fileName, String uri, HttpServletResponse response)
                throws ServletException,
                IOException {

            String filePath = getFilePath(fileName);

            if (filePath.endsWith(".html")) {
                response.setContentType("text/html; charset=utf-8");
            }
            if (fileName.endsWith(".jpg")) {
                byte[] bytes = Utils.readByteArrayFromResource(filePath);
                if (bytes != null) {
                    response.getOutputStream().write(bytes);
                }

                return;
            }

            String text = Utils.readFromResource(filePath);
            if (text == null) {
                return;
            }

            if (fileName.endsWith(".css")) {
                response.setContentType("text/css;charset=utf-8");
            } else if (fileName.endsWith(".js")) {
                response.setContentType("text/javascript;charset=utf-8");
            }
            response.getWriter().write(text);
        }

        protected String getFilePath(String fileName) {
            return resourcePath + fileName;
        }

        public void service(HttpServletRequest request
                , HttpServletResponse response
                , String servletPath
                , ProcessCallback processCallback
        ) throws ServletException, IOException {
            String contextPath = request.getContextPath();
            String requestURI = request.getRequestURI();

            response.setCharacterEncoding("utf-8");

            if (contextPath == null) { // root context
                contextPath = "";
            }
            String uri = contextPath + servletPath;
            String path = requestURI.substring(contextPath.length() + servletPath.length());

            if ("".equals(path) || "/".equals(path)) {
                returnResourceFile("/http.resources/index.html", uri, response);
                return;
            }

            if (path.contains(".json")) {
                String fullUrl = path;
                if (request.getQueryString() != null && request.getQueryString().length() > 0) {
                    fullUrl += "?" + request.getQueryString();
                }
                response.getWriter().print(processCallback.process(fullUrl));
                return;
            }

            returnResourceFile(path, uri, response);
        }


    }
}
