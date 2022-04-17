package com.github.ryanlove.coordinator.itempublish.enums;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * BizTag
 * @author ryan
 */
public enum BizTag {
    ONE_PRICE(101,"ONE_PRICE"),
    COUPON(102,"COUPON"),
    RENT(103,"RENT"),
    PRE_SALE(201,"PRE_SALE")
    ;


    BizTag(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BizTag getByTagName(String tagName){
        for (BizTag b : values()) {
            if(b.name.equals(tagName.toLowerCase())){
                return b;
            }
        }
        return null;
    }
}
