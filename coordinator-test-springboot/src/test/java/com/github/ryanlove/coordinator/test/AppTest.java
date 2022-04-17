package com.github.ryanlove.coordinator.test;

import com.github.ryanlove.coordinator.itempublish.domain.ItemDO;
import com.github.ryanlove.coordinator.itempublish.service.ItemService;
import com.github.ryanlove.coordinator.util.Builder;
import com.github.ryanlove.coordinator.util.ItemTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {


    @Autowired
    ItemService itemService;


    @Test
    public void runSaveItem() {

        ItemDO itemBean = Builder.of(ItemDO::new)
                .with(ItemDO::setItemId,9999L)
                .with(ItemDO::setName,"苹果显示器")
                .with(ItemDO::setStatus,1)
                .with(ItemDO::setType, ItemTypeEnum.COUPON.getKey())
                .with(ItemDO::setPreSale,true)
                .build();


        Boolean result = itemService.save(itemBean);
        Assert.assertTrue(result);

    }




}

