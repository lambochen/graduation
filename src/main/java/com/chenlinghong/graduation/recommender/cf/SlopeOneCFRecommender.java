package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description Slope One Recommender
 * @Author chenlinghong
 * @Date 2019/4/28 16:42
 * @Version V1.0
 */
public class SlopeOneCFRecommender extends AbstractSlopeOneRecommender {

    /**
     * 无参构造
     */
    public SlopeOneCFRecommender() {

    }

    /**
     * 添加数据源，使用默认初始化
     *
     * @param dataSource
     * @throws TasteException
     */
    public SlopeOneCFRecommender(DataSource dataSource) throws TasteException {
        defaultInit(dataSource);
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId) throws TasteException {
        /**
         * 默认推荐10条
         */
        return recommendGraduation(userId, NumericConstant.TEN);
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId, int recommendNum) throws TasteException {
        return converter(userId, recommend(userId, recommendNum));
    }

    @Override
    public List<RecommendedItem> recommend(long userId) throws TasteException {
        /**
         * 默认推荐10条数据
         */
        return recommend(userId, NumericConstant.TEN);
    }

    @Override
    public List<RecommendedItem> recommend(long userId, int recommendNum) throws TasteException {
        /**
         * 调用Mahout推荐
         */
        return recommender.recommend(userId, recommendNum);
    }

    /**
     * 默认初始化块
     *
     * @param dataSource
     */
    protected void defaultInit(DataSource dataSource) throws TasteException {
        // this.dataSource = dataSource;
        initDataSource();

        this.dataModel = new GraduationMysqlDataModel(mysqlDataSource);
        this.recommender = new CachingRecommender(new SlopeOneRecommender(dataModel));
    }

}
