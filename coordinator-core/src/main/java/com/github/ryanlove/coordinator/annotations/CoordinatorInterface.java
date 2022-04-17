package com.github.ryanlove.coordinator.annotations;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * CoordinatorInterface
 * @author ryan
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface CoordinatorInterface {
    String desc() default "";
}
