package com.chenlinghong.graduation.converter;

import com.chenlinghong.graduation.repository.domain.Goods;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @Description 推荐系统转换器
 * @Author chenlinghong
 * @Date 2019/4/30 23:08
 * @Version V1.0
 */
public interface RecommenderConvertor extends Converter {

    /**
     * 由推荐Item转换为Goods列表
     *
     * @param itemList  推荐系统推荐结果
     * @return
     */
    List<Goods> convert(List<RecommendedItem> itemList);

}
