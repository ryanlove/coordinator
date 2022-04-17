package com.github.ryanlove.coordinator.reducer;


import com.github.ryanlove.coordinator.configuration.MethodInfo;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * AllTrue
 *
 * @author ryan
 */

public class AllTrue extends Reducer<Boolean> {

    @Override
    public Boolean reduce(Object... args) throws Exception {
        boolean expect = true;
        for(MethodInfo methodInfo: this.getMethodInfoList()){
            Object result = MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), args);
            if(!result.equals(true)){
                expect = false;
                break;
            }
        }
        return expect;
    }
}
