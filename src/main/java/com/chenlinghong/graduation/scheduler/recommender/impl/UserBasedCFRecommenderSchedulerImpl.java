package com.chenlinghong.graduation.scheduler.recommender.impl;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.cf.UserBasedCFRecommender;
import com.chenlinghong.graduation.scheduler.recommender.AbstractRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @Description 基于用户的协同过滤推荐执行器实现类
 * @Author chenlinghong
 * @Date 2019/4/30 22:32
 * @Version V1.0
 */
@Slf4j
@Service
public class UserBasedCFRecommenderSchedulerImpl
        extends AbstractRecommenderScheduler implements UserBasedCFRecommenderScheduler {

    @Autowired
    private DataSource dataSource;

    /**
     * 邻居数
     */
    private static final int neighborhoodNumber = NumericConstant.TEN;

    @PostConstruct
    private void init() throws TasteException {
        recommender = new UserBasedCFRecommender(dataSource, neighborhoodNumber);
    }

    // @Override
    // public RecommendDto recommend(final long userId) throws TasteException {
    //     /**
    //      * 默认推荐10条数据
    //      */
    //     return recommend(userId, NumericConstant.TEN);
    // }

    // @Override
    // public RecommendDto recommend(final long userId, final int recommendNum) throws TasteException {
    //     if (userId <= 0 || recommendNum <= 0) {
    //         log.error("UserBasedCFRecommenderScheduler#recommend: param is illegal. userId={}, " +
    //                 "recommendNum={}.", userId, recommendNum);
    //         return null;
    //     }
    //     RecommendDto<RecommendGoodsDto> result = new RecommendDto<>();
    //     result.setUserId(userId);
    //     /**
    //      * 填充User
    //      */
    //     User user = getUser(userId);
    //     result.setUser(user);
    //     /**
    //      * 获取推荐结果
    //      */
    //     PageDto<RecommendGoodsDto> data = recommendByCF(userId, recommendNum);
    //     result.setData(data);
    //     return result;
    // }

}
