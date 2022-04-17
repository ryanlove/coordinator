package com.github.ryanlove.coordinator.spring;


import com.github.ryanlove.coordinator.annotations.CoordinatorInterface;
import com.github.ryanlove.coordinator.annotations.CoordinatorTags;
import com.github.ryanlove.coordinator.core.CoordinatorBean;
import com.github.ryanlove.coordinator.core.CoordinatorRegister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan
 */
@Component
public class CoordinatorBeanScanner implements BeanPostProcessor {


    private Map<String, Map<String, CoordinatorBean>> coordinatorBeans = new HashMap<>();

    @Autowired
    CoordinatorRegister coordinatorRegister;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        CoordinatorBean coordinatorBean = bean instanceof CoordinatorBean?(CoordinatorBean) bean : null;
        if(coordinatorBean == null) {
            return bean;
        }

        Class<?> clazz = coordinatorBean.getClass();
        CoordinatorInterface coordinatorInterface = AnnotationUtils.findAnnotation(clazz, CoordinatorInterface.class);
        if (coordinatorInterface == null) {
            return bean;
        }


        Class<?>[] classInterfaces = clazz.getSuperclass().getInterfaces();

        for (Class<?> clz : classInterfaces) {

            Map<String, CoordinatorBean> groupBeans = coordinatorBeans.get(clz.getName());
            if (CollectionUtils.isEmpty(groupBeans)) {
                groupBeans = new HashMap<>();
            }
            CoordinatorTags coordinatorTags = AnnotationUtils.findAnnotation(clazz, CoordinatorTags.class);
            if (coordinatorTags != null) {
                String bizTag = coordinatorTags.tagName();
                groupBeans.put(bizTag, coordinatorBean);
                coordinatorBeans.put(clz.getName(), groupBeans);
            }
        }

        if(coordinatorBeans.size() == 0) {
            return bean;
        }
        coordinatorRegister.register(coordinatorBeans);
        return bean;
    }

}

