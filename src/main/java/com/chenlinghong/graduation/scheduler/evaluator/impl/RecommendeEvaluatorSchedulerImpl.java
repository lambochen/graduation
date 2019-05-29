package com.chenlinghong.graduation.scheduler.evaluator.impl;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.repository.dao.RecommenderEvalutorDao;
import com.chenlinghong.graduation.repository.domain.RecommenderEvalutor;
import com.chenlinghong.graduation.scheduler.evaluator.RecommendeEvaluatorScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.ItemBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.SlopeOneCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/5/30 4:20
 * @Version V1.0
 */
@Service
@Slf4j
public class RecommendeEvaluatorSchedulerImpl implements RecommendeEvaluatorScheduler {

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

    @Autowired
    private RecommenderEvalutorDao recommenderEvalutorDao;

    @Override
    // @Async(value = AsyncNameConstant.EVALUTOR)
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

    @Override
    @Async(value = AsyncNameConstant.EVALUTOR)
    public void evaluate(RecommendTypeEnum typeEnum) throws TasteException {
        double score = evaluateScore(typeEnum);
        IRStatistics irStatistics = evaluateIRStatistics(typeEnum);
        RecommenderEvalutor recommenderEvalutor = new RecommenderEvalutor();
        recommenderEvalutor.setScore(score);
        recommenderEvalutor.setType(typeEnum.getCode());
        if (irStatistics != null) {
            recommenderEvalutor.setPrecision(irStatistics.getPrecision());
            recommenderEvalutor.setRecall(irStatistics.getRecall());
        }
        int result = recommenderEvalutorDao.insert(recommenderEvalutor);
        if (result != NumericConstant.ONE) {
            // 插入失败
            log.error("RecommendeEvaluatorService#evalutor: failed to insert. recommendEvalutor=[{}]", recommenderEvalutor);
            throw new AsyncBusinessException(ErrorEnum.EVALUTOR_FAILED_INSERT);
        }
    }

}
