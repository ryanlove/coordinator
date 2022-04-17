package com.github.ryanlove.coordinator.itempublish.procedure;

import com.github.ryanlove.coordinator.annotations.CoordinatorTags;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;

/**
 * @author ryan
 */
@CoordinatorTags(tagName = "COUPON")
public class CouponPostPublishProcedure implements PostPublishProcedure {
    @Override
    public Boolean postPublishExtPoint(ItemDO itemBean) {
        System.out.println("PostPublishProcedure 发布完成【券】");
        return true;
    }
}
