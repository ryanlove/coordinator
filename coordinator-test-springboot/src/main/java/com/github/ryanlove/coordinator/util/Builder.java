package com.github.ryanlove.coordinator.util;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author ryan
 */
public class Builder<T> {
     private T t;

     private Builder(T t){
         this.t = t;
     }

     public static <T> Builder<T> of(Supplier<T> supplier){
         return new Builder<>(supplier.get());
     }

     public static <T> Builder<T> of(T t){
         return new Builder<>(t);
     }

     public <P> Builder<T> with(BiConsumer<T,P> biConsumer,P p) {
         biConsumer.accept(t,p);
         return this;
     }

     public T build(){
         return this.t;
     }
}
