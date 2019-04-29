package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.Goods;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsDaoTest {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void insert() {
        Goods goods = new Goods();
        goods.setBusinessInfoId(1000L);
        goods.setCatalogOneId(1);
        goods.setCatalogTwoId(1);
        goods.setName("test");
        goods.setDescription("test");
        goods.setPrice(1.0);
        goods.setRemarks("test");
        goods.setGoodsInfo("test  ");
        goods.setCoverImg("http://pic37.nipic.com/20140113/8800276_184927469000_2.png");
        int result = goodsDao.insert(goods);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<Goods> goodsList = goodsDao.listAll(0, 10);
        System.out.println(goodsList);
    }

}