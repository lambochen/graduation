package com.chenlinghong.graduation.recommender.builder;

import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.sql.DataSource;

/**
 * @Description 基于用户推荐构造器
 * @Author chenlinghong
 * @Date 2019/5/29 23:14
 * @Version V1.0
 */
public class UserBasedRecommenderBuilder implements RecommenderBuilder {

    /**
     * user similarity matrix
     */
    protected UserSimilarity userSimilarity;

    /**
     * neighborhood
     */
    protected UserNeighborhood userNeighborhood;

    /**
     * 邻居个数,默认20
     */
    protected int neighborhoodNumber;

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

    public UserBasedRecommenderBuilder() {

    }

    public UserBasedRecommenderBuilder(DataSource dataSource, final int neighborhoodNumber) throws TasteException {
        this.dataSource = dataSource;
        this.dataModel = new GraduationMysqlDataModel(this.dataSource);
        // 邻居个数，默认20
        this.neighborhoodNumber = neighborhoodNumber;
        /**
         * 相似度矩阵、邻居用户采用默认实现
         */
        this.userSimilarity = new PearsonCorrelationSimilarity(this.dataModel);
        this.userNeighborhood =
                new NearestNUserNeighborhood(neighborhoodNumber, this.userSimilarity, this.dataModel);
    }

    @Override
    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
        return new CachingRecommender(
                new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity));
    }
}
