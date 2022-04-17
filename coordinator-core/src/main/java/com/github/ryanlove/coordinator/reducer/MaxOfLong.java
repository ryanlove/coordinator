package com.github.ryanlove.coordinator.reducer;


import com.github.ryanlove.coordinator.configuration.MethodInfo;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * @author ryan
 */
public class MaxOfLong extends Reducer<Long> {

    @Override
    public Long reduce(Object... args) throws Exception {
        long expect = Long.MIN_VALUE;

        for(MethodInfo methodInfo: this.getMethodInfoList()){
            Integer result = (Integer) MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), args);
            expect = Long.max(result,expect);
        }
        return expect;
    }
}
