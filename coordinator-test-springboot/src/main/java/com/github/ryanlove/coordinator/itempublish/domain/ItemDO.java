package com.github.ryanlove.coordinator.itempublish.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ryan
 */
public class ItemDO implements Serializable {
    private static final long serialVersionUID = 7848723974194806868L;

    private Long itemId;
    private Integer type;
    private Integer status;
    private String name;
    private Map bizMap;

    private Boolean preSale = false;

    private Integer businessId;


    public ItemDO() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getBizMap() {
        return bizMap;
    }

    public void setBizMap(Map bizMap) {
        this.bizMap = bizMap;
    }

    public Boolean getPreSale() {
        return preSale;
    }

    public void setPreSale(Boolean preSale) {
        this.preSale = preSale;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
}
