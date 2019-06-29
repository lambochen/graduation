package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCatalogOneDaoTest {

    @Autowired
    private GoodsCatalogOneDao catalogOneDao;

    @Test
    public void insert() {
        GoodsCatalogOne catalogOne = new GoodsCatalogOne("test-1");
        int result = catalogOneDao.insert(catalogOne);
        Assert.assertEquals(1, result);
        System.out.println(result);
    }

    @Test
    public void listAll() {
        List<GoodsCatalogOne> catalogOneList = catalogOneDao.listAll(0, 10);
        System.out.println(catalogOneList);
    }

}