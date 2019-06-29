package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.google.common.collect.Lists;
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

    @Test
    public void listByIdList() {
        List<Long> idList = Lists.newLinkedList();
        idList.add(1L);
        idList.add(2L);
        List<Goods> goodsList = goodsDao.listByIdList(idList);
        System.out.println(goodsList);
    }

    @Test
    public void listByCatalogTwoList() {
        List<GoodsCatalogTwo> catalogTwoList = Lists.newLinkedList();
        GoodsCatalogTwo two = new GoodsCatalogTwo();
        two.setId(1L);
        catalogTwoList.add(two);
        List<Goods> goodsList = goodsDao.listByCatalogTwoList(catalogTwoList,0,3);
        System.out.println(goodsList);
    }

}