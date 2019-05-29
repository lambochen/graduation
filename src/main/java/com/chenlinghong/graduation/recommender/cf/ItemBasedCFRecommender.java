package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.AbstractMahoutRecommender;
import com.chenlinghong.graduation.recommender.builder.ItemBasedRecommenderBuilder;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description 基于物品的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/4/28 9:36
 * @Version V1.0
 */
public class ItemBasedCFRecommender extends AbstractMahoutRecommender {

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
        dataModel = new GraduationMysqlDataModel(this.dataSource);
        recommenderBuilder = new ItemBasedRecommenderBuilder();
        recommender = recommenderBuilder.buildRecommender(dataModel);
    }

}
