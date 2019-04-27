package com.chenlinghong.graduation.recommender;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @Description 推荐器
 * @Author chenlinghong
 * @Date 2019/4/27 17:45
 * @Version V1.0
 */
public interface Recommender {

    /**
     * 采用默认的紧邻计算，推荐默认条数据
     *
     * @param userId
     * @return
     */
    List<RecommendedItem> recommend(long userId);

    /**
     * 指定推荐数目
     *
     * @param userId
     * @param recommendNum
     * @return
     */
    List<RecommendedItem> recommend(long userId, int recommendNum);

}
