package com.chenlinghong.graduation.scheduler.recommender.impl;

import com.chenlinghong.graduation.recommender.cf.ItemBasedCFRecommender;
import com.chenlinghong.graduation.scheduler.recommender.AbstractRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.ItemBasedCFRecommenderScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @Description 基于物品的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/5/1 21:35
 * @Version V1.0
 */
@Slf4j
@Service
public class ItemBasedCFRecommenderSchedulerImpl
        extends AbstractRecommenderScheduler implements ItemBasedCFRecommenderScheduler {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init() throws TasteException {
        recommender = new ItemBasedCFRecommender(dataSource);
    }

}
