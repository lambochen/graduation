package com.chenlinghong.graduation.recommender;

import com.chenlinghong.graduation.evaluator.MahoutRecommendeEvaluator;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.google.common.collect.Lists;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description graduation mahout 抽象推荐类
 * @Author chenlinghong
 * @Date 2019/4/28 9:56
 * @Version V1.0
 */
public abstract class AbstractMahoutRecommender implements MahoutRecommender {

    /**
     * recommender builder
     */
    protected RecommenderBuilder recommenderBuilder;

    /**
     * recommender
     */
    protected Recommender recommender;

    /**
     * MySQL DataModel
     */
    protected DataModel dataModel;

    /**
     * data source
     */
    protected DataSource dataSource;

    // protected MysqlDataSource mysqlDataSource;

    @Override
    public double evaluateScore() throws TasteException {
        return MahoutRecommendeEvaluator.evaluateScore(recommenderBuilder, dataModel);
    }


    @Override
    public double evaluateScore(RecommenderBuilder recommenderBuilder, DataModel dataModel,
                                double trainingPercentage, double evaluationPercentage) throws TasteException {
        return MahoutRecommendeEvaluator.evaluateScore(
                recommenderBuilder, dataModel, trainingPercentage, evaluationPercentage);
    }

    @Override
    public IRStatistics evaluateIRStatistics() throws TasteException {
        return MahoutRecommendeEvaluator.evaluateIRStatistics(recommenderBuilder, dataModel);
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
    @Override
    public IRStatistics evaluateIRStatistics(RecommenderBuilder recommenderBuilder,
                                             DataModel dataModel,
                                             IDRescorer rescorer,
                                             int at,
                                             double relevanceThreshold,
                                             double evaluationPercentage) throws TasteException {
        return MahoutRecommendeEvaluator.evaluateIRStatistics(
                recommenderBuilder, dataModel, rescorer, at, relevanceThreshold, evaluationPercentage);
    }

    /**
     * 数据转换,
     *
     * @param userId              用户ID
     * @param recommendedItemList Mahout推荐项
     * @return
     */
    protected List<GraduationRecommendItem> converter(final long userId,
                                                      final List<RecommendedItem> recommendedItemList) {
        List<GraduationRecommendItem> result = Lists.newArrayList();
        for (RecommendedItem item : recommendedItemList) {
            GraduationRecommendItem graduationRecommendItem = new GraduationRecommendItem();
            graduationRecommendItem.setUserId(userId);
            graduationRecommendItem.setItemId(item.getItemID());
            graduationRecommendItem.setPreference(item.getValue());
            result.add(graduationRecommendItem);
        }
        return result;
    }


}
