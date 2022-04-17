package com.github.ryanlove.coordinator.core;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略工厂
 * 用于识别对象所属身份识别器
 * @author ryan
 */
public class IdentifyResolverStrategy {

    private static final Map<String,IdentifyResolver> resolverMap = new ConcurrentHashMap<>();


    private  Map<String,String> resolverConfig;


    @Autowired
    private ApplicationContext applicationContext;

    public IdentifyResolverStrategy(Map<String,String> resolverConfig) {
        this.resolverConfig = resolverConfig;
    }
    public  Identify findIdentifier(Object businessDO) throws InvocationTargetException, IllegalAccessException, Exception {
        return resolverMap.computeIfAbsent(businessDO.getClass().getName(), this::createResolver)
                .identify(businessDO);
    }


    private  IdentifyResolver createResolver(String type) {
        String targetResolverName = resolverConfig.getOrDefault(type,"");
        if(StringUtils.isEmpty(targetResolverName)) {
            return new NoneIdentifyResolver();
        }

        IdentifyResolver target = applicationContext.getBean(targetResolverName,IdentifyResolver.class);
        if(target == null) {
            return new NoneIdentifyResolver();
        }

        return target;
    }

}
