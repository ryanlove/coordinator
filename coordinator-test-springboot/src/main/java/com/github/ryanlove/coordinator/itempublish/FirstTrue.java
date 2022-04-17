package com.github.ryanlove.coordinator.itempublish;

import com.github.ryanlove.coordinator.configuration.MethodInfo;
import com.github.ryanlove.coordinator.reducer.Reducer;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Created with IntelliJ IDEA.
 * FirstTrue
 *
 * @author ryan
 * @description:
 */
public class FirstTrue extends Reducer<Boolean> {

    @Override
    public Boolean reduce(Object... args) throws Exception {
        for(MethodInfo methodInfo: this.getMethodInfoList()){
            System.out.println("FirstTrue execute");
            Object result = MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), args);
            return result.equals(true);
        }
        return false;
    }
}

