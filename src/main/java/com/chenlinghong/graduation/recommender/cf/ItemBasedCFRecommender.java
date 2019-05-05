package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description 基于物品的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/4/28 9:36
 * @Version V1.0
 */
public class ItemBasedCFRecommender extends AbstractItemBasedRecommender {

    /**
     * 无参构造
     */
    public ItemBasedCFRecommender() {

    }

    /**
     * 传入数据源构造
     *
     * @param dataSource 数据源
     * @throws TasteException
     */
    public ItemBasedCFRecommender(DataSource dataSource) throws TasteException {
        defaultInit(dataSource);
    }

    @Override
    public List<RecommendedItem> recommend(long userId) throws TasteException {
        /**
         * 默认推荐10条
         */
        return recommend(userId, NumericConstant.TEN);
    }

    @Override
    public List<RecommendedItem> recommend(long userId, int recommendNum) throws TasteException {
        // Mahout推荐
        return recommender.recommend(userId, recommendNum);
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId) throws TasteException {
        return converter(userId, recommend(userId));
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId, int recommendNum) throws TasteException {
        return converter(userId, recommend(userId, recommendNum));
    }

    /**
     * 默认初始化方法
     *
     * @param dataSource 数据源
     * @throws TasteException
     */
    protected void defaultInit(DataSource dataSource) throws TasteException {
        this.dataSource = dataSource;
        // initDataSource();

        this.dataModel = new GraduationMysqlDataModel(this.dataSource);
        this.recommenderBuilder = new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
                ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
                return new GenericItemBasedRecommender(model, similarity);
            }
        };
        this.recommender = new CachingRecommender(this.recommenderBuilder.buildRecommender(dataModel));
    }

}
