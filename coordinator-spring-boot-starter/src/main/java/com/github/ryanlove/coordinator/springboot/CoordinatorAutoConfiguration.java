package com.github.ryanlove.coordinator.springboot;


import com.github.ryanlove.coordinator.core.CoordinatorRegister;
import com.github.ryanlove.coordinator.core.DefaultRegister;
import com.github.ryanlove.coordinator.core.IdentifyResolverStrategy;
import org.springframework.aop.Advisor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ryan
 */
@Configuration
@EnableConfigurationProperties({GroupTagProperties.class})
@Import({CoordinatorConfigViewServletConfiguration.class})
public class CoordinatorAutoConfiguration {

    @Bean
    IdentifyResolverStrategy identifyResolverStrategy(GroupTagProperties groupTagProperties){
        Map<String,String> configMap  = groupTagProperties.getIdentifyResolvers()
                .stream().collect(Collectors.toMap(GroupTagProperties.resolverConfig::getTargetModel, GroupTagProperties.resolverConfig::getResolver));
        return new IdentifyResolverStrategy(configMap);
    }


    @Bean
    public CoordinatorRegister coordinatorRegister() {
        return new DefaultRegister();
    }

    @Bean
    public Advisor coordinatorAdvisor(GroupTagProperties groupTagProperties,IdentifyResolverStrategy identifyResolverStrategy,CoordinatorRegister coordinatorRegister){
        CoordinatorAnnotationInterceptor advice = new CoordinatorAnnotationInterceptor(groupTagProperties,identifyResolverStrategy,coordinatorRegister);
        return new CoordinatorAdvisor(advice);
    }
}
