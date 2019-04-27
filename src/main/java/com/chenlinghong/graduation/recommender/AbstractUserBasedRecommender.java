package com.chenlinghong.graduation.recommender;

import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * @Description 基于用户的推荐
 * @Author chenlinghong
 * @Date 2019/4/27 22:45
 * @Version V1.0
 */
public abstract class AbstractUserBasedRecommender implements Recommender {

    /**
     * 用户相似度矩阵
     */
    protected UserSimilarity userSimilarity;

    /**
     * 邻居
     */
    protected UserNeighborhood userNeighborhood;

    // /**
    //  * 推荐
    //  *
    //  * @param userId          用户ID
    //  * @param recommendNum    推荐数目
    //  * @param neighborhoodNum 邻居个数
    //  * @return
    //  */
    // public abstract List<RecommendedItem> recommend(long userId, int recommendNum, int neighborhoodNum);

}
