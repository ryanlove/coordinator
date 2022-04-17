package com.github.ryanlove.coordinator.itempublish.procedure;

import com.github.ryanlove.coordinator.annotations.CoordinatorTags;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;

/**
 * @author ryan
 */
@CoordinatorTags(tagName = "ONE_PRICE")
public class OnePricePostPublishProcedure implements PostPublishProcedure {
    @Override
    public Boolean postPublishExtPoint(ItemDO itemBean) {
        System.out.println("PostPublishProcedure 发布完成【一口价】");
        return true;
    }
}
