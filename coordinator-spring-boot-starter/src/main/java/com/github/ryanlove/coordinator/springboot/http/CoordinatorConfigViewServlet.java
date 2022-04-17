package com.github.ryanlove.coordinator.springboot.http;

import com.github.ryanlove.coordinator.springboot.http.service.ICoordinatorConfigService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ryan
 */
public class CoordinatorConfigViewServlet extends AbstractResourceServlet {

    private ICoordinatorConfigService configService;

    public CoordinatorConfigViewServlet(ICoordinatorConfigService configService){
        super("http/resources");
        this.configService=configService;
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 上下文路径，比如 ""
        String contextPath = request.getContextPath();
        // servlet路径，比如 /coordinator
        String servletPath = request.getServletPath();
        // 请求路径，比如 /coordinator/index.html
        String requestURI = request.getRequestURI();
        // 设置编码格式
        response.setCharacterEncoding("utf-8");

        if (contextPath == null) { // root context
            contextPath = "";
        }
        // 获得约定的对应的资源路径为 "" 或者如 /index.html
        String path = requestURI.substring(contextPath.length() + servletPath.length());

        if ("".equals(path)) {
            if (contextPath.equals("") || contextPath.equals("/")) {
                response.sendRedirect("/coordinator/index.html");
            } else {
                response.sendRedirect("coordinator/index.html");
            }
            return;
        }
        if ("/".equals(path)) {
            response.sendRedirect("http.resources/index.html");
            return;
        }

        super.service(request, response);
    }

    @Override
    protected String process(String url) {
        return configService.service(url);
    }
}
