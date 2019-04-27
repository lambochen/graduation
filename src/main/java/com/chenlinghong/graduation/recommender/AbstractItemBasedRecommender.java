package com.chenlinghong.graduation.recommender;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;

/**
 * @Description 抽象推荐器
 * @Author chenlinghong
 * @Date 2019/4/27 22:31
 * @Version V1.0
 */
public abstract class AbstractItemBasedRecommender implements Recommender {

    /**
     * Recommender builder
     */
    protected RecommenderBuilder recommenderBuilder;

}
