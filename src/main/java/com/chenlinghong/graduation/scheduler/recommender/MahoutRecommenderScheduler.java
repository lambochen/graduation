package com.chenlinghong.graduation.scheduler.recommender;

import com.chenlinghong.graduation.scheduler.Scheduler;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @Description 推荐调度器
 * @Author chenlinghong
 * @Date 2019/4/29 10:22
 * @Version V1.0
 */
public interface MahoutRecommenderScheduler extends Scheduler {

    /**
     * 推荐固定条数
     *
     * @param userId 用户ID
     * @return
     */
    RecommendDto recommend(final long userId) throws TasteException;

    /**
     * 推荐
     *
     * @param userId       用户ID
     * @param recommendNum 推荐条数
     * @return
     */
    RecommendDto recommend(final long userId, final int recommendNum) throws TasteException;

}
