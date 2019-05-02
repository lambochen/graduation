package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.recommender.AbstractMahoutRecommender;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;

/**
 * @Description 基于物品推荐器
 * @Author chenlinghong
 * @Date 2019/4/27 22:31
 * @Version V1.0
 */
public abstract class AbstractItemBasedRecommender extends AbstractMahoutRecommender {

    /**
     * Recommender builder
     */
    protected RecommenderBuilder recommenderBuilder;

}
