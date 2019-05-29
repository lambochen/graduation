package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.AbstractMahoutRecommender;
import com.chenlinghong.graduation.recommender.builder.UserBasedRecommenderBuilder;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description 基于用户的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/4/27 21:14
 * @Version V1.0
 */
public class UserBasedCFRecommender extends AbstractMahoutRecommender {

    public UserBasedCFRecommender() {

    }

    /**
     * @param dataSource 数据源
     * @throws TasteException
     */
    public UserBasedCFRecommender(final DataSource dataSource) throws TasteException {
        defaultInit(dataSource, NumericConstant.TWENTY);
    }

    /**
     * @param dataSource         数据源
     * @param neighborhoodNumber 邻居
     * @throws TasteException
     */
    public UserBasedCFRecommender(DataSource dataSource, final int neighborhoodNumber) throws TasteException {
        defaultInit(dataSource, neighborhoodNumber);
    }

    @Override
    public List<RecommendedItem> recommend(final long userId) throws TasteException {
        /**
         * 默认推荐10条数据
         */
        return recommend(userId, NumericConstant.TEN);
    }

    @Override
    public List<RecommendedItem> recommend(final long userId, final int recommendNum) throws TasteException {
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
     * @param dataSource         数据源
     * @param neighborhoodNumber 邻居个数
     * @throws TasteException
     */
    private void defaultInit(final DataSource dataSource, final int neighborhoodNumber) throws TasteException {
        this.dataSource = dataSource;
        this.dataModel = new GraduationMysqlDataModel(this.dataSource);
        recommenderBuilder = new UserBasedRecommenderBuilder(dataSource, neighborhoodNumber);
        recommender = recommenderBuilder.buildRecommender(this.dataModel);
    }

}
