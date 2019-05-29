package com.chenlinghong.graduation.scheduler.recommender;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.converter.RecommenderConverter;
import com.chenlinghong.graduation.recommender.AbstractMahoutRecommender;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 推荐执行器抽象类
 * @Author chenlinghong
 * @Date 2019/5/1 22:17
 * @Version V1.0
 */
@Service
public abstract class AbstractMahoutRecommenderScheduler {

    @Autowired
    protected RecommenderConverter recommenderConverter;

    protected AbstractMahoutRecommender recommender;

    public RecommendDto recommend(final long userId) throws TasteException {
        /**
         * 默认推荐10条数据
         */
        return recommend(userId, NumericConstant.TEN);
    }

    public RecommendDto<RecommendGoodsDto> recommend(long userId, int recommendNum) throws TasteException {
        if (userId <= 0 || recommendNum <= 0) {
            // log.error("UserBasedCFRecommenderScheduler#recommend: param is illegal. userId={}, " +
            //         "recommendNum={}.", userId, recommendNum);
            return null;
        }
        /**
         * 1、填充User信息
         * 2、获取推荐结果
         */
        // 填充userId
        RecommendDto result = new RecommendDto<>();
        result.setUserId(userId);
        // 填充User基本信息
        User user = getUser(userId);
        result.setUser(user);
        // 填充推荐数据
        PageDto<RecommendGoodsDto> data = recommendByCF(userId, recommendNum);
        result.setData(data);
        return result;
    }

    /**
     * 获取推荐结果
     *
     * @param userId
     * @return
     */
    protected PageDto<RecommendGoodsDto> recommendByCF(final long userId, final int recommendNum) throws TasteException {
        /**
         * 获取推荐结果
         */
        List<RecommendedItem> recommendData = recommender.recommend(userId, recommendNum);
        if (recommendData == null || recommendData.size() <= 0) {
            return null;
        }
        /**
         * 转换数据
         */
        List<RecommendGoodsDto> goodsList = recommenderConverter.convert(recommendData);
        PageDto<RecommendGoodsDto> result = new PageDto<>(goodsList);
        return result;
    }

    /**
     * 获取User
     *
     * @param userId
     * @return
     */
    protected User getUser(long userId) {
        /**
         * TODO 从缓存中读取User
         */
        return null;
    }

    /**
     * 计算评分
     *
     * @return
     */
    public double evaluateScore() throws TasteException {
        return recommender.evaluateScore();
    }

    /**
     * 计算评分
     *
     * @param recommenderBuilder   推荐构造器
     * @param dataModel            数据模型
     * @param trainingPercentage   训练百分比
     * @param evaluationPercentage 评估百分比
     * @return
     * @throws TasteException
     */
    public double evaluateScore(RecommenderBuilder recommenderBuilder,
                                DataModel dataModel,
                                double trainingPercentage,
                                double evaluationPercentage) throws TasteException {
        return recommender.evaluateScore(recommenderBuilder, dataModel, trainingPercentage, evaluationPercentage);
    }

    /**
     * 计算查全率，召回率等
     *
     * @return
     * @throws TasteException
     */
    public IRStatistics evaluateIRStatistics() throws TasteException {
        return recommender.evaluateIRStatistics();
    }

    /**
     * 计算查全率和查准率
     *
     * @param recommenderBuilder   推荐算法构造器
     * @param dataModel            数据模型
     * @param rescorer             如果有的话，在计算推荐时使用
     * @param at                   如，“精确度为5”。评估精度时要考虑的建议数量，
     * @param relevanceThreshold   优先值至少为该值的项目被认为是“相关的”用于计算的目的
     * @param evaluationPercentage 评估百分比
     * @return
     * @throws TasteException
     */
    public IRStatistics evaluateIRStatistics(RecommenderBuilder recommenderBuilder,
                                             DataModel dataModel,
                                             IDRescorer rescorer,
                                             int at,
                                             double relevanceThreshold,
                                             double evaluationPercentage) throws TasteException {
        return recommender.evaluateIRStatistics(recommenderBuilder, dataModel, rescorer, at, relevanceThreshold,
                evaluationPercentage);
    }

}
