package com.chenlinghong.graduation.recommender;

import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.google.common.collect.Lists;
import lombok.NonNull;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @Description graduation mahout 抽象推荐类
 * @Author chenlinghong
 * @Date 2019/4/28 9:56
 * @Version V1.0
 */
public abstract class AbstractGraduationMahoutRecommender implements MahoutRecommender, GraduationRecommender {

    /**
     * 数据转换,
     *
     * @param userId              用户ID
     * @param recommendedItemList Mahout推荐项
     * @return
     */
    protected List<GraduationRecommendItem> converter(final long userId,
                                                      @NonNull final List<RecommendedItem> recommendedItemList) {
        List<GraduationRecommendItem> result = Lists.newArrayList();
        for (RecommendedItem item : recommendedItemList) {
            GraduationRecommendItem graduationRecommendItem = new GraduationRecommendItem();
            graduationRecommendItem.setUserId(userId);
            graduationRecommendItem.setItemId(item.getItemID());
            graduationRecommendItem.setPreference(item.getValue());
            result.add(graduationRecommendItem);
        }
        return result;
    }

}
