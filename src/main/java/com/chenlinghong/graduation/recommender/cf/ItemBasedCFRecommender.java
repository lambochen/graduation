package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.recommender.AbstractItemBasedRecommender;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @Description 基于物品的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/4/28 9:36
 * @Version V1.0
 */
public class ItemBasedCFRecommender extends AbstractItemBasedRecommender {

    @Override
    public List<RecommendedItem> recommend(long userId) throws TasteException {
        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userId, int recommendNum) throws TasteException {
        return null;
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId) {
        return null;
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId, int recommendNum) {
        return null;
    }
}
