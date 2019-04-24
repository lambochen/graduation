package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserBehavior;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBehaviorDaoTest {

    @Autowired
    private UserBehaviorDao behaviorDao;

    @Test
    public void insert() {
        UserBehavior behavior = new UserBehavior(3, 1, 1);
        int result = behaviorDao.insert(behavior);
        assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<UserBehavior> behaviorList = behaviorDao.listAll(0, 10);
        System.out.println(behaviorList);
    }

}