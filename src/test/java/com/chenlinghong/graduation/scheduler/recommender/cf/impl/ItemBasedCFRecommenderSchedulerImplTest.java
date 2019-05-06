package com.chenlinghong.graduation.scheduler.recommender.cf.impl;

import com.chenlinghong.graduation.scheduler.recommender.cf.ItemBasedCFRecommenderScheduler;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemBasedCFRecommenderSchedulerImplTest {

    @Autowired
    private ItemBasedCFRecommenderScheduler itemBasedCFRecommenderScheduler;

    @Test
    public void refreshRecommendQueue() throws TasteException {
        itemBasedCFRecommenderScheduler.refreshRecommendQueue(2);
    }
}