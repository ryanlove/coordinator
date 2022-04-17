package com.github.ryanlove.coordinator.reducer;


import com.github.ryanlove.coordinator.configuration.MethodInfo;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * @author ryan
 */
public class MaxOfInt extends Reducer<Integer> {

    @Override
    public Integer reduce(Object... args) throws Exception {
        int expect = Integer.MIN_VALUE;

        for(MethodInfo methodInfo: this.getMethodInfoList()){
            Integer result = (Integer) MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), args);
            expect = Integer.max(result,expect);
        }
        return expect;
    }
}
