package com.github.ryanlove.coordinator.itempublish.procedure;


import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 发布后默认处理实现
 * @author ryan
 */
@Primary
@Component
public class DefaultPostPublishProcedure implements PostPublishProcedure {
    @Override
    public Boolean postPublishExtPoint(ItemDO itemBean) {
        return false;
    }
}
