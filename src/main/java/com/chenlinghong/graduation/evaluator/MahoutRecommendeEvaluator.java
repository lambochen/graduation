package com.chenlinghong.graduation.evaluator;

import com.chenlinghong.graduation.constant.NumericConstant;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;

/**
 * @Description 推荐评估器
 * @Author chenlinghong
 * @Date 2019/5/30 0:08
 * @Version V1.0
 */
public class MahoutRecommendeEvaluator {

    /**
     * 训练百分比
     */
    public static double DEFAULT_TRAINING_PERCENTAGE = 0.7;

    /**
     * 评估百分比
     */
    public static double DEFAULT_EVALUATION_PERCENTAGE = 1.0;

    /**
     * 默认at值
     */
    public static int DEFAULT_AT = NumericConstant.TEN;

    /**
     * 全量
     */
    public static double FULL_EVALUATION_PERCENTAGE = 1.0;


    /**
     * 计算评估评分
     *
     * @param recommenderBuilder   推荐算法构造器
     * @param dataModelBuilder     数据模型构造器
     * @param dataModel            数据模型
     * @param trainingPercentage   训练百分比
     * @param evaluationPercentage 评估百分比
     * @return
     */
    public static double evaluateScore(RecommenderBuilder recommenderBuilder,
                                       DataModelBuilder dataModelBuilder,
                                       DataModel dataModel,
                                       double trainingPercentage,
                                       double evaluationPercentage) throws TasteException {
        /**
         * 计算评分
         */
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        double score = evaluator.evaluate(
                recommenderBuilder, dataModelBuilder, dataModel, trainingPercentage, evaluationPercentage);
        return score;
    }

    /**
     * 计算评估评分
     *
     * @param recommenderBuilder   推荐构造器
     * @param dataModel            数据模型
     * @param trainingPercentage   训练百分比
     * @param evaluationPercentage 评估百分比
     * @return
     * @throws TasteException
     */
    public static double evaluateScore(RecommenderBuilder recommenderBuilder, DataModel dataModel,
                                       double trainingPercentage, double evaluationPercentage) throws TasteException {
        return evaluateScore(recommenderBuilder, null, dataModel, trainingPercentage, evaluationPercentage);
    }

    /**
     * 计算评估评分
     *
     * @param recommenderBuilder 推荐构造器
     * @param dataModel          数据模型
     * @return
     * @throws TasteException
     */
    public static double evaluateScore(RecommenderBuilder recommenderBuilder, DataModel dataModel) throws TasteException {
        return evaluateScore(recommenderBuilder, dataModel, DEFAULT_TRAINING_PERCENTAGE, DEFAULT_EVALUATION_PERCENTAGE);
    }


    /**
     * 计算查全率和查准率
     *
     * @param recommenderBuilder   推荐算法构造器
     * @param dataModelBuilder     数据模型构造器
     * @param dataModel            数据模型
     * @param rescorer             如果有的话，在计算推荐时使用
     * @param at                   如，“精确度为5”。评估精度时要考虑的建议数量，*等
     * @param relevanceThreshold   优先值至少为该值的项目被认为是“相关的”用于计算的目的
     * @param evaluationPercentage 评估百分比
     * @return
     * @throws TasteException
     */
    public static IRStatistics evaluateIRStatistics(RecommenderBuilder recommenderBuilder,
                                                    DataModelBuilder dataModelBuilder,
                                                    DataModel dataModel,
                                                    IDRescorer rescorer,
                                                    int at,
                                                    double relevanceThreshold,
                                                    double evaluationPercentage) throws TasteException {
        RecommenderIRStatsEvaluator statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        IRStatistics result = statsEvaluator.evaluate(
                recommenderBuilder, dataModelBuilder, dataModel, rescorer, at, relevanceThreshold, evaluationPercentage);
        return result;
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
    public static IRStatistics evaluateIRStatistics(RecommenderBuilder recommenderBuilder,
                                                    DataModel dataModel,
                                                    IDRescorer rescorer,
                                                    int at,
                                                    double relevanceThreshold,
                                                    double evaluationPercentage) throws TasteException {
        return evaluateIRStatistics(
                recommenderBuilder, null, dataModel, rescorer, at, relevanceThreshold, evaluationPercentage);
    }

    /**
     * 计算查全率和查准率
     *
     * @param recommenderBuilder 推荐算法构造器
     * @param dataModel          数据模型
     * @return
     * @throws TasteException
     */
    public static IRStatistics evaluateIRStatistics(
            RecommenderBuilder recommenderBuilder, DataModel dataModel) throws TasteException {
        return evaluateIRStatistics(recommenderBuilder, dataModel, null, DEFAULT_AT,
                GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, FULL_EVALUATION_PERCENTAGE);
    }


}
