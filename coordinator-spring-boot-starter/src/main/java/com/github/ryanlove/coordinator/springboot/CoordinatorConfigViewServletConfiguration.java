package com.github.ryanlove.coordinator.springboot;

import com.github.ryanlove.coordinator.core.CoordinatorRegister;
import com.github.ryanlove.coordinator.springboot.http.CoordinatorConfigViewServlet;
import com.github.ryanlove.coordinator.springboot.http.service.impl.CoordinatorConfigServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


/**
 * @author ryan
 */
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "spring.coordinator.config-view-servlet.enabled", havingValue = "true")
public class CoordinatorConfigViewServletConfiguration {

    @Bean
    public CoordinatorConfigServiceImpl coordinatorConfigService(GroupTagProperties groupTagProperties, CoordinatorRegister coordinatorRegister){
        CoordinatorConfigServiceImpl coordinatorConfigService = new CoordinatorConfigServiceImpl();
        coordinatorConfigService.setGroupTagProperties(groupTagProperties);
        coordinatorConfigService.setCoordinatorRegister(coordinatorRegister);
        return coordinatorConfigService;
    }

    @Bean
    public CoordinatorConfigViewServlet coordinatorConfigViewServlet(CoordinatorConfigServiceImpl coordinatorConfigService){
        return new CoordinatorConfigViewServlet(coordinatorConfigService);
    }

    @Bean
    public ServletRegistrationBean configViewServletRegistrationBean(CoordinatorConfigViewServlet coordinatorConfigViewServlet) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(coordinatorConfigViewServlet);
        registrationBean.addUrlMappings("/coordinator/*");
        return registrationBean;
    }
}
