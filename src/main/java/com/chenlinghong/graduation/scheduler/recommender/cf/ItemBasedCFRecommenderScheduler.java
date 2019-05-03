package com.chenlinghong.graduation.scheduler.recommender.cf;

import com.chenlinghong.graduation.scheduler.recommender.MahoutRecommenderScheduler;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @Description 基于物品的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/5/1 21:34
 * @Version V1.0
 */
public interface ItemBasedCFRecommenderScheduler extends MahoutRecommenderScheduler {

    /**
     * 刷新推荐队列
     *
     * @param userId 用户ID
     * @return
     */
    Long refreshRecommendQueue(long userId) throws TasteException;

}
