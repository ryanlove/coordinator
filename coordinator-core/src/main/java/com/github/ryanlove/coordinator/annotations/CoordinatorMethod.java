package com.github.ryanlove.coordinator.annotations;


import com.github.ryanlove.coordinator.reducer.Reducer;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * CoordinatorMethod
 * @author ryan
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface CoordinatorMethod {
    String desc() default "";

    Class<? extends Reducer> reducer();
}
