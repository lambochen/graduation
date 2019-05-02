package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.recommender.AbstractMahoutRecommender;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * @Description 基于用户的推荐
 * @Author chenlinghong
 * @Date 2019/4/27 22:45
 * @Version V1.0
 */
public abstract class AbstractUserBasedRecommender extends AbstractMahoutRecommender {

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
    protected Integer neighborhoodNumber;

}
