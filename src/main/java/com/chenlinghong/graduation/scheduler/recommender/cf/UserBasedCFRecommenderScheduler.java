package com.chenlinghong.graduation.scheduler.recommender.cf;

import com.chenlinghong.graduation.scheduler.recommender.MahoutRecommenderScheduler;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @Description 基于用户推荐调度器
 * @Author chenlinghong
 * @Date 2019/4/29 10:19
 * @Version V1.0
 */
public interface UserBasedCFRecommenderScheduler extends MahoutRecommenderScheduler {

    /**
     * 刷新推荐队列
     *
     * @param userId 用户ID
     * @return
     */
    Long refreshRecommendQueue(long userId) throws TasteException;


}
