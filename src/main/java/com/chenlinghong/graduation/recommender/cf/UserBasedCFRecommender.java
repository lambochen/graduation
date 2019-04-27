package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.AbstractUserBasedRecommender;
import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Description 基于用户的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/4/27 21:14
 * @Version V1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserBasedCFRecommender extends AbstractUserBasedRecommender {

    /**
     * MySQL DataModel
     */
    private volatile GraduationMysqlDataModel mysqlDataModel;

    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * 邻居个数,默认20
     */
    private int neighborhoodNumber;

    /**
     * @param dataSource 数据源
     * @throws TasteException
     */
    public UserBasedCFRecommender(DataSource dataSource) throws TasteException {
        defaultInit(dataSource, NumericConstant.TWENTY);
    }

    /**
     *
     * @param dataSource         数据源
     * @param neighborhoodNumber 邻居
     * @throws TasteException
     */
    public UserBasedCFRecommender(DataSource dataSource, int neighborhoodNumber) throws TasteException {
        defaultInit(dataSource, neighborhoodNumber);
    }

    @Override
    public List<RecommendedItem> recommend(long userId) {
        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userId, int recommendNum) {
        return null;
    }

    /**
     * 默认初始化方法
     *
     * @param dataSource         数据源
     * @param neighborhoodNumber 邻居个数
     * @throws TasteException
     */
    private void defaultInit(final DataSource dataSource, final int neighborhoodNumber) throws TasteException {
        this.mysqlDataModel = new GraduationMysqlDataModel(dataSource);
        // 邻居个数，默认20
        this.neighborhoodNumber = neighborhoodNumber;
        /**
         * 相似度矩阵、邻居用户采用默认实现
         */
        this.userSimilarity = new PearsonCorrelationSimilarity(this.mysqlDataModel);
        this.userNeighborhood =
                new NearestNUserNeighborhood(neighborhoodNumber, this.userSimilarity, this.mysqlDataModel);
    }

}
