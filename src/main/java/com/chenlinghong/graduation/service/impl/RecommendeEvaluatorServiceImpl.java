package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.scheduler.recommender.cf.ItemBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.SlopeOneCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.service.RecommendeEvaluatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 推荐评估器Service
 * @Author chenlinghong
 * @Date 2019/5/30 1:30
 * @Version V1.0
 */
@Service
@Slf4j
public class RecommendeEvaluatorServiceImpl implements RecommendeEvaluatorService {

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

    @Override
    public double evaluateScore(RecommendTypeEnum typeEnum) throws TasteException {
        double result = -1;
        if (typeEnum == RecommendTypeEnum.USER_BASED_RECOMMEND) {
            // 基于用户的协同过滤推荐
            result = userBasedCFRecommenderScheduler.evaluateScore();
        } else if (typeEnum == RecommendTypeEnum.ITEM_BASED_RECOMMEND) {
            // 基于用户的协同过滤推荐
            result = itemBasedCFRecommenderScheduler.evaluateScore();
        } else if (typeEnum == RecommendTypeEnum.SLOPE_ONE_RECOMMEND) {
            // SlopeOne的协同过滤推荐
            result = slopeOneCFRecommenderScheduler.evaluateScore();
        } else {
            // 数据有误
        }
        return result;
    }

    @Override
    public IRStatistics evaluateIRStatistics(RecommendTypeEnum typeEnum) throws TasteException {
        IRStatistics result = null;
        if (typeEnum == RecommendTypeEnum.USER_BASED_RECOMMEND) {
            // 基于用户的协同过滤推荐
            result = userBasedCFRecommenderScheduler.evaluateIRStatistics();
        } else if (typeEnum == RecommendTypeEnum.ITEM_BASED_RECOMMEND) {
            // 基于用户的协同过滤推荐
            result = itemBasedCFRecommenderScheduler.evaluateIRStatistics();
        } else if (typeEnum == RecommendTypeEnum.SLOPE_ONE_RECOMMEND) {
            // SlopeOne的协同过滤推荐
            result = slopeOneCFRecommenderScheduler.evaluateIRStatistics();
        } else {
            // 数据有误
        }
        return result;
    }
}
