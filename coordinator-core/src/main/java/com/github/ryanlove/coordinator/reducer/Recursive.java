package com.github.ryanlove.coordinator.reducer;

import com.github.ryanlove.coordinator.configuration.MethodInfo;
import org.apache.commons.lang3.reflect.MethodUtils;



/**
 * 递归调用策略
 * @author ryan
 */
public class Recursive extends Reducer<Object> {
    @Override
    public Object reduce(Object... args) throws Exception {
        Object expect = null;

        for(MethodInfo methodInfo: this.getMethodInfoList()){
            if(expect == null) {
                expect =  MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), args);
            }
           expect =  MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), expect);
        }
        return expect;
    }
}
