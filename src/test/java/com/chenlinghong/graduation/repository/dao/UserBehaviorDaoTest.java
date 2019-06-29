package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
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

    @Test
    public void batchInsertByUserBehavior() {
        List<UserBehavior> behaviorList = Lists.newArrayList();
        UserBehavior behavior = new UserBehavior(3, 2, 2);
        UserBehavior behavior2 = new UserBehavior(3, 1, 2);
        behaviorList.add(behavior);
        behaviorList.add(behavior2);
        int result = behaviorDao.batchInsertByUserBehavior(behaviorList);
        Assert.assertEquals(2, result);
    }

    @Test
    public void listByUserAndGoodsAndStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        List<UserBehavior> userBehaviorList =
                behaviorDao.listByUserAndGoodsAndStartTime(3, 2, calendar.getTime());
        System.out.println(userBehaviorList);
    }

}