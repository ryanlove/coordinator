package com.github.ryanlove.coordinator.core;


import com.github.ryanlove.coordinator.configuration.MethodInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan
 */
public class DefaultRegister implements CoordinatorRegister {

    private Map<String, Map<String, CoordinatorBean>> coordinatorBeans =new HashMap<String, Map<String, CoordinatorBean>>();


    private static final Map<String, MethodInfo> methodInfoMap = new HashMap<String, MethodInfo>();

    @Override
    public void register(MethodInfo methodInfo) {
        String key = methodInfo.getCoordinatorBean().toString() + "_" + methodInfo.getTargetMethod().getName();
        methodInfoMap.putIfAbsent(key, methodInfo);
    }

    @Override
    public MethodInfo find(String key) {
        return methodInfoMap.get(key);
    }

    @Override
    public void clear() {
        methodInfoMap.clear();
    }

    @Override
    public void register(Map<String, Map<String, CoordinatorBean>> coordinatorBeans) {
        this.coordinatorBeans = coordinatorBeans;
    }

    @Override
    public Map<String, CoordinatorBean> findByClassName(String className) {
        return coordinatorBeans.get(className);
    }

    @Override
    public Map<String, Map<String, CoordinatorBean>> findAll() {
        return coordinatorBeans;
    }
}
