package com.github.ryanlove.coordinator.reducer;



import com.github.ryanlove.coordinator.configuration.MethodInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Reducer
 * 规约策略接口
 * @author ryan
 */
public abstract class Reducer<T> {

    private List<MethodInfo> methodInfoList;

    public abstract  T reduce(Object... args) throws Exception;

    public Reducer(){

    }

    public List<MethodInfo> getMethodInfoList() {
        return methodInfoList;
    }

    public void setMethodInfoList(List<MethodInfo> methodInfoList) {
        this.methodInfoList = methodInfoList;
    }



}
