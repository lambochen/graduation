package com.chenlinghong.graduation.recommender.builder;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

/**
 * @Description 基于物品的协同过滤推荐构造器
 * @Author chenlinghong
 * @Date 2019/5/29 23:56
 * @Version V1.0
 */
public class ItemBasedRecommenderBuilder implements RecommenderBuilder {


    @Override
    public Recommender buildRecommender(DataModel model) throws TasteException {
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        return new CachingRecommender(new GenericItemBasedRecommender(model, similarity));
    }
}
