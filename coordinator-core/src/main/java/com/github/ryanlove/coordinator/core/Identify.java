package com.github.ryanlove.coordinator.core;


/**
 * 业务身份标识
 * @author ryan
 */
public class Identify {

    String bizTagName;
    String bizTagGroupName;

    public String getBizTagName() {
        return bizTagName;
    }

    public void setBizTagName(String bizTagName) {
        this.bizTagName = bizTagName;
    }

    public String getBizTagGroupName() {
        return bizTagGroupName;
    }

    public void setBizTagGroupName(String bizTagGroupName) {
        this.bizTagGroupName = bizTagGroupName;
    }
}
