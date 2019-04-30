package com.chenlinghong.graduation.recommender;

import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import org.apache.mahout.cf.taste.common.TasteException;

import java.util.List;

/**
 * @Description 推荐器
 * @Author chenlinghong
 * @Date 2019/4/27 17:45
 * @Version V1.0
 */
public interface GraduationRecommender extends Recommender {

    /**
     * 采用默认的紧邻计算，推荐默认条数据
     *
     * @param userId
     * @return
     */
    List<GraduationRecommendItem> recommendGraduation(final long userId) throws TasteException;

    /**
     * 指定推荐数目
     *
     * @param userId
     * @param recommendNum
     * @return
     */
    List<GraduationRecommendItem> recommendGraduation(final long userId, final int recommendNum) throws TasteException;


}
