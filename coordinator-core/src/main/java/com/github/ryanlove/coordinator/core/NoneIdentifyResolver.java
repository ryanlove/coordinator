package com.github.ryanlove.coordinator.core;

/**
 * 无身份识别器
 * @author ryan
 */
public class NoneIdentifyResolver implements IdentifyResolver{
    @Override
    public Identify identify(Object domain) {
        return new Identify();
    }
}
