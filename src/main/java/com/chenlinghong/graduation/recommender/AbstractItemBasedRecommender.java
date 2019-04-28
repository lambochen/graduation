package com.chenlinghong.graduation.recommender;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;

import javax.sql.DataSource;

/**
 * @Description 基于物品推荐器
 * @Author chenlinghong
 * @Date 2019/4/27 22:31
 * @Version V1.0
 */
public abstract class AbstractItemBasedRecommender extends AbstractGraduationMahoutRecommender {

    /**
     * Recommender builder
     */
    protected RecommenderBuilder recommenderBuilder;

    /**
     * recommender
     */
    protected Recommender recommender;

    /**
     * MySQL DataModel
     */
    protected DataModel dataModel;

    /**
     * 数据源
     */
    protected DataSource dataSource;

}
