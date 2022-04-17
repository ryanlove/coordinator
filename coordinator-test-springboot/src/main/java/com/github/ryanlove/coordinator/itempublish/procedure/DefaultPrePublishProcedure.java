package com.github.ryanlove.coordinator.itempublish.procedure;


import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 发布前默认处理实现
 * @author ryan
 */
@Primary
@Component
public class DefaultPrePublishProcedure implements PrePublishProcedure {
    @Override
    public Boolean validate(ItemDO itemDO) {
        return true;
    }
}
