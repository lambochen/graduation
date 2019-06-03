package com.chenlinghong.graduation.scheduler.recommender;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.scheduler.Scheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.ItemBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.SlopeOneCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.season.SeasonBasedRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.user.UserTagBasedRecommenderScheduler;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description 推荐队列执行器
 * @Author chenlinghong
 * @Date 2019/5/4 9:40
 * @Version V1.0
 */
@Service
public class RecommendQueueScheduler implements Scheduler {

    /**
     * 基于用户的协同过滤推荐执行器
     */
    @Autowired
    private UserBasedCFRecommenderScheduler userBasedCFRecommenderScheduler;

    /**
     * 基于物品的协同过滤推荐执行器
     */
    @Autowired
    private ItemBasedCFRecommenderScheduler itemBasedCFRecommenderScheduler;

    /**
     * SlopeOne协同过滤推荐执行器
     */
    @Autowired
    private SlopeOneCFRecommenderScheduler slopeOneCFRecommenderScheduler;

    /**
     * 时令推荐
     */
    @Autowired
    private SeasonBasedRecommenderScheduler seasonBasedRecommenderScheduler;

    /**
     * 基于用户标签推荐
     */
    @Autowired
    private UserTagBasedRecommenderScheduler userTagBasedRecommenderScheduler;

    /**
     * 刷新推荐队列
     *
     * @param userId   用户ID
     * @param typeEnum 推荐类型
     * @return 新写入数据数目
     */
    @Async(value = AsyncNameConstant.SCHEDULER)
    public void refreshRecommendQueue(long userId, RecommendTypeEnum typeEnum) throws TasteException {
        switch (typeEnum) {
            /**
             * 基于用户的协同过滤推荐
             */
            case USER_BASED_RECOMMEND:
                userBasedCFRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 基于物品推荐
             */
            case ITEM_BASED_RECOMMEND:
                itemBasedCFRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * SlopeOne推荐，基于评分推荐
             */
            case SLOPE_ONE_RECOMMEND:
                slopeOneCFRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 时令推荐
             */
            case SEASON_RECOMMEND:
                seasonBasedRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 基于用户标签的推荐
             */
            case USER_TAG_BASED_RECOMMEND:
                userTagBasedRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 热门推荐
             */
            case POPULAR_RECOMMEND:
                break;
        }
    }


}
