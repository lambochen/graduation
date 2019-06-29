package com.chenlinghong.graduation.scheduler.recommender.cf.impl;

import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBasedCFRecommenderSchedulerImplTest {

    @Resource(name = "userBasedCFRecommenderScheduler")
    private UserBasedCFRecommenderScheduler userBasedCFRecommenderScheduler;

    @Test
    public void refreshRecommendQueue() throws TasteException {
        userBasedCFRecommenderScheduler.refreshRecommendQueue(1);
    }
}