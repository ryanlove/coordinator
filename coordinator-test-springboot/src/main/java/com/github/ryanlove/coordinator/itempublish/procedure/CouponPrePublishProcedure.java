package com.github.ryanlove.coordinator.itempublish.procedure;


import com.github.ryanlove.coordinator.annotations.CoordinatorTags;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;

/**
 * @author ryan
 */
@CoordinatorTags(tagName = "COUPON")
public class CouponPrePublishProcedure implements PrePublishProcedure {

    @Override
    public Boolean validate(ItemDO itemDO) {
        System.out.println("PrePublishProcedure.validate 验证商品数据【券】");

        return true;
    }
}
