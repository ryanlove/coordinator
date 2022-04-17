package com.github.ryanlove.coordinator.itempublish.procedure;

import com.github.ryanlove.coordinator.annotations.CoordinatorInterface;
import com.github.ryanlove.coordinator.annotations.CoordinatorMethod;
import com.github.ryanlove.coordinator.core.CoordinatorBean;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import com.github.ryanlove.coordinator.reducer.AllTrue;

/**
 * @author ryan
 */
@CoordinatorInterface(desc = "发布前调用")
public interface PrePublishProcedure extends CoordinatorBean {

    @CoordinatorMethod(reducer = AllTrue.class, desc = "验证商品数据")
    Boolean validate(ItemDO itemDO);
}
