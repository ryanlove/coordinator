package com.github.ryanlove.coordinator.itempublish.procedure;


import com.github.ryanlove.coordinator.annotations.CoordinatorInterface;
import com.github.ryanlove.coordinator.annotations.CoordinatorMethod;
import com.github.ryanlove.coordinator.core.CoordinatorBean;
import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import com.github.ryanlove.coordinator.reducer.AllTrue;
import com.github.ryanlove.coordinator.reducer.FirstNotNull;
import com.github.ryanlove.coordinator.reducer.Recursive;

/**
 * @author ryan
 */
@CoordinatorInterface(desc = "发布后调用")
public interface PostPublishProcedure extends CoordinatorBean {

    @CoordinatorMethod(reducer = AllTrue.class, desc = "发布后调用的方法")
    Boolean postPublishExtPoint(ItemDO itemBean);
}
