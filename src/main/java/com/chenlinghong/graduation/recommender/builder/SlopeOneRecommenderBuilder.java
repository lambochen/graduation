package com.chenlinghong.graduation.recommender.builder;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/5/30 2:21
 * @Version V1.0
 */
public class SlopeOneRecommenderBuilder implements RecommenderBuilder {
    @Override
    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
        return new CachingRecommender(new SlopeOneRecommender(dataModel));
    }
}
