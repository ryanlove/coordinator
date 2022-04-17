package com.github.ryanlove.coordinator.itempublish.procedure;


import com.github.ryanlove.coordinator.annotations.CoordinatorTags;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;

/**
 * @author ryan
 */
@CoordinatorTags(tagName = "PRE_SALE")
public class PreSalePostPublishProcedure implements PostPublishProcedure {
    @Override
    public Boolean postPublishExtPoint(ItemDO itemBean) {
        System.out.println("PostPublishProcedure 商品发布完成【预售】");

        return true;
    }
}
