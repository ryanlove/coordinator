package com.github.ryanlove.coordinator.itempublish.service;

import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;

/**
 * 商品服务
 * @author ryan
 */
public interface ItemService {

    /**
     * 保存商品
     * @param itemDO
     * @return
     */
    Boolean save(ItemDO itemDO);
}
