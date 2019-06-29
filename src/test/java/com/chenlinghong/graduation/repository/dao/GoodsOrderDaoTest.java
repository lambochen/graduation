package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsOrderDaoTest {

    @Autowired
    private GoodsOrderDao orderDao;

    @Test
    public void insert() {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setName("name");
        goodsOrder.setGoodsId(1000L);
        goodsOrder.setDescription("test");
        goodsOrder.setPrice(1.0);
        goodsOrder.setRemarks("test");
        goodsOrder.setUserId(1000L);
        goodsOrder.setPostCountry("中国");
        goodsOrder.setNumber(2);
        int result = orderDao.insert(goodsOrder);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<GoodsOrder> goodsOrderList = orderDao.listAll(0, 10);
        System.out.println(goodsOrderList);
    }

}