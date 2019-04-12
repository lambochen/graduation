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
        goods.setCatalogOneId(1000);
        goods.setCatalogTwoId(1000);
        goods.setName("test");
        goods.setDescription("test");
        goods.setPrice(1.0);
        goods.setRemarks("test");
        goods.setGoodsInfo("test  ");
        int result = goodsDao.insert(goods);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<Goods> goodsList = goodsDao.listAll(0, 10);
        System.out.println(goodsList);
    }

    @Test
    public void listByGoodsCatalogOneId() {
        long count = goodsDao.countByCatalogOne(1);
        List<Goods> goodsList = goodsDao.listByCatalogOne(1,0,count);
        System.out.println(goodsList);
    }

    @Test
    public void listByGoodsCatalogTwoId() {
        long count =  goodsDao.countByCatalogTwo(1);
        List<Goods> goodsList = goodsDao.listByCatalogTwo(1,0,count);
        System.out.println(goodsList);
    }

    @Test
    public void getGoodsById() {
        Goods goods= goodsDao.getById(1L);
        System.out.println(goods);
    }
}