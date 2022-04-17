package com.github.ryanlove.coordinator.configuration;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * MethodInfo
 * * @author ryan
 */
public class MethodInfo {

    //spring bean
    private Object CoordinatorBean;

    private Method targetMethod;


    public MethodInfo(){

    }

    public Object getCoordinatorBean() {
        return CoordinatorBean;
    }

    public void setCoordinatorBean(Object coordinatorBean) {
        CoordinatorBean = coordinatorBean;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }
}
