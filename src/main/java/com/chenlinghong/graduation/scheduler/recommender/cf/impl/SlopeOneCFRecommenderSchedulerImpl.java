package com.chenlinghong.graduation.scheduler.recommender.cf.impl;

import com.chenlinghong.graduation.recommender.cf.SlopeOneCFRecommender;
import com.chenlinghong.graduation.scheduler.recommender.AbstractMahoutRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.SlopeOneCFRecommenderScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @Description SlopeOne 基于评分推荐实现类
 * @Author chenlinghong
 * @Date 2019/5/1 22:34
 * @Version V1.0
 */
@Service
@Slf4j
public class SlopeOneCFRecommenderSchedulerImpl
        extends AbstractMahoutRecommenderScheduler implements SlopeOneCFRecommenderScheduler {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init() throws TasteException {
        recommender = new SlopeOneCFRecommender(dataSource);
    }


}
