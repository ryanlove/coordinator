package com.github.ryanlove.coordinator.itempublish.service.impl;

import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import com.github.ryanlove.coordinator.itempublish.procedure.PostPublishProcedure;
import com.github.ryanlove.coordinator.itempublish.procedure.PrePublishProcedure;
import com.github.ryanlove.coordinator.itempublish.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * ItemServiceImpl
 *
 * @author ryan
 */
@Component
public class ItemServiceImpl implements ItemService {


    @Autowired
    PrePublishProcedure prePublishProcedure;

    @Autowired
    PostPublishProcedure postPublishProcedure;

    @Override

    public Boolean save(ItemDO itemDO) {


        //改造后
        Boolean validateResult = prePublishProcedure.validate(itemDO);

        System.out.println("保存商品前验证结果 :   " + validateResult );

        Boolean postPublishExtPointResult = postPublishProcedure.postPublishExtPoint(itemDO);

        System.out.println("保存商品结果 ： " + postPublishExtPointResult);


        return postPublishExtPointResult;
    }

}
