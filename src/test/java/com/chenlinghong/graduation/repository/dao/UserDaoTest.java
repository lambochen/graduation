package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void insert() {
        User user = new User("13008142306");
        int result = userDao.insert(user);
        Assert.assertEquals(1, result);
        System.out.println(user.getId());
    }

    @Test
    public void getById() {
        int id = 2;
        User user = userDao.getById(id);
        System.out.println(user);
    }

}