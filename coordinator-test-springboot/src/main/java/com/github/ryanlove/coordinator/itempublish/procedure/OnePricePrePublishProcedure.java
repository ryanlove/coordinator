package com.github.ryanlove.coordinator.itempublish.procedure;


import com.github.ryanlove.coordinator.annotations.CoordinatorTags;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;

/**
 * @author ryan
 */
@CoordinatorTags(tagName = "ONE_PRICE")
public class OnePricePrePublishProcedure implements PrePublishProcedure {

    @Override
    public Boolean validate(ItemDO itemDO) {
        System.out.println("PrePublishProcedure.validate 验证商品数据【一口价】 ");
        return true;
    }
}
