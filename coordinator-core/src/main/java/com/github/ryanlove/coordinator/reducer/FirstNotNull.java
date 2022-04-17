package com.github.ryanlove.coordinator.reducer;

import com.github.ryanlove.coordinator.configuration.MethodInfo;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * FirstNotNull
 *
 * @author ryan
 */

public class FirstNotNull extends Reducer<Object> {

    @Override
    public Object reduce(Object... args) throws Exception {
        for (MethodInfo methodInfo : this.getMethodInfoList()) {
            Object result = MethodUtils.invokeExactMethod(methodInfo.getCoordinatorBean(), methodInfo.getTargetMethod().getName(), args);
            if (result != null) {
                return result;
            }
        }
        System.out.println("every method return null");
        return null;
    }
}
