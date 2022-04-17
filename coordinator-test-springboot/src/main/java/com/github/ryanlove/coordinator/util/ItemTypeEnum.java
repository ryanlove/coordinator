package com.github.ryanlove.coordinator.util;

/**
 * @author ryan
 */

public enum ItemTypeEnum {
    ONE_PRICE(1, "一口价"),
    COUPON(2, "券"),
    RENT(3, "租赁商品"),
    PRE_SALE(4,"预售");

    private Integer key;
    private String name;

    private ItemTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

}