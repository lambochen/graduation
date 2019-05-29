package com.chenlinghong.graduation.scheduler.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;

/**
 * @Description 推荐调度器
 * @Author chenlinghong
 * @Date 2019/4/29 10:22
 * @Version V1.0
 */
public interface MahoutRecommenderScheduler extends RecommenderScheduler {

    /**
     * 计算评分
     *
     * @return
     */
    double evaluateScore() throws TasteException;

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
    double evaluateScore(RecommenderBuilder recommenderBuilder, DataModel dataModel,
                         double trainingPercentage, double evaluationPercentage) throws TasteException;

    /**
     * 计算查全率、查准率等
     *
     * @return
     */
    IRStatistics evaluateIRStatistics() throws TasteException;

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
    IRStatistics evaluateIRStatistics(RecommenderBuilder recommenderBuilder,
                                      DataModel dataModel,
                                      IDRescorer rescorer,
                                      int at,
                                      double relevanceThreshold,
                                      double evaluationPercentage) throws TasteException;

}
