package com.github.ryanlove.coordinator.core;


/**
 * 身份识别器
 * 不同系统可实现各自业务身份识别器
 * @author ryan
 */
public interface IdentifyResolver {

    Identify identify(Object domain) throws Exception;
}
