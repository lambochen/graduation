package com.chenlinghong.graduation.recommender;

import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * @Description 基于用户的推荐
 * @Author chenlinghong
 * @Date 2019/4/27 22:45
 * @Version V1.0
 */
public abstract class AbstractUserBasedRecommender implements MahoutRecommender, GraduationRecommender {

    /**
     * 用户相似度矩阵
     */
    protected UserSimilarity userSimilarity;

    /**
     * 邻居
     */
    protected UserNeighborhood userNeighborhood;

    /**
     * recommender
     */
    protected org.apache.mahout.cf.taste.recommender.Recommender recommender;

}
