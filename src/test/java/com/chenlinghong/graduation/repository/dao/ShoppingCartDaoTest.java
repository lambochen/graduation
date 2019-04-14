package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartDaoTest {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Test
    public void insert() {
        ShoppingCart shoppingCart = new ShoppingCart(2L, null, 2L, 1);
        int result = shoppingCartDao.insert(shoppingCart);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listByUser() {
        List<ShoppingCart> shoppingCartList = shoppingCartDao.listByUser(2L, 0L, 10L);
        System.out.println(shoppingCartList);
    }

    @Test
    public void deleteByIdList() {
        List<Long> idList = new LinkedList<>();
        idList.add(1L);
        idList.add(2L);

        int count = shoppingCartDao.deleteByIdList(idList , 1);
        Assert.assertEquals(2, count);
    }

    @Test
    public void updateCount(){
        shoppingCartDao.updateCount(1,3);
    }

}