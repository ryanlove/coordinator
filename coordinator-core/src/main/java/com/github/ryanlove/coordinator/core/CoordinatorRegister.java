package com.github.ryanlove.coordinator.core;


import com.github.ryanlove.coordinator.configuration.MethodInfo;

import java.util.Map;

/**
 * 注册器
 * 注册带有CoordinatorInterface注解的实现类的方法
 * key = className+MethodName;
 * @author ryan
 */
public interface CoordinatorRegister {

    void register(MethodInfo methodInfo);

    MethodInfo find(String key);

    void clear();

    void register(Map<String, Map<String, CoordinatorBean>> coordinatorBeans);

    Map<String, CoordinatorBean> findByClassName(String className);

    Map<String, Map<String, CoordinatorBean>> findAll();
}
