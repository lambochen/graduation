package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @Description Slope One Recommender
 * @Author chenlinghong
 * @Date 2019/4/28 16:42
 * @Version V1.0
 */
public class SlopeOneCFRecommender extends AbstractSlopeOneRecommender {
    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId) throws TasteException {
        return null;
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId, int recommendNum) throws TasteException {
        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userId) throws TasteException {
        return null;
    }

    @Override
    public List<RecommendedItem> recommend(long userId, int recommendNum) throws TasteException {
        return null;
    }
}
