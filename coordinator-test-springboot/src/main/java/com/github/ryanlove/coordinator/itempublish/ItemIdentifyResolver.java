package com.github.ryanlove.coordinator.itempublish;

import com.github.ryanlove.coordinator.core.Identify;
import com.github.ryanlove.coordinator.core.IdentifyResolver;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import com.github.ryanlove.coordinator.itempublish.enums.BizTag;
import com.github.ryanlove.coordinator.itempublish.enums.BizTagGroup;
import com.github.ryanlove.coordinator.util.ItemTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author ryan
 */
@Component("ItemIdentifyResolver")
public class ItemIdentifyResolver implements IdentifyResolver {
    @Override
    public Identify identify(Object domain) {
        Identify identify = new Identify();
        ItemDO itemDO = (ItemDO) domain;

        if(ItemTypeEnum.ONE_PRICE.getKey().equals(itemDO.getType())) {
            identify.setBizTagName(BizTag.ONE_PRICE.getName());
        }

        if(ItemTypeEnum.ONE_PRICE.getKey().equals(itemDO.getType()) && itemDO.getPreSale()) {
            identify.setBizTagGroupName(BizTagGroup.GROUP_ONE_PRICE.getName());
        }

        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())) {
            identify.setBizTagName(BizTag.COUPON.getName());
        }
        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())
                && itemDO.getPreSale()) {
            identify.setBizTagGroupName(BizTagGroup.GROUP_COUPON.getName());
        }

        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())) {
            identify.setBizTagName(BizTag.COUPON.getName());
        }
        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())
                && itemDO.getPreSale()) {
            identify.setBizTagGroupName(BizTagGroup.GROUP_COUPON.getName());
        }

        return identify;
    }
}
