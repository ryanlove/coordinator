package com.github.ryanlove.coordinator.itempublish.enums;


/**
 * Created with IntelliJ IDEA.
 * <p>
 * BizTagGroup
 */
public enum BizTagGroup {
    GROUP_ONE_PRICE(1,"GROUP_ONE_PRICE"),
    GROUP_COUPON(2,"GROUP_COUPON");


    BizTagGroup(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    private Integer key;
    private String name;



    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
