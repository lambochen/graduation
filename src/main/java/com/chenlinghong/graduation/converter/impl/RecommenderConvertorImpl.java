package com.chenlinghong.graduation.converter.impl;

import com.chenlinghong.graduation.converter.RecommenderConvertor;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.GoodsService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 推荐系统转换类实现类
 * @Author chenlinghong
 * @Date 2019/4/30 23:10
 * @Version V1.0
 */
@Slf4j
@Service
public class RecommenderConvertorImpl implements RecommenderConvertor {

    @Autowired
    private GoodsService goodsService;

    @Override
    public List<RecommendGoodsDto> convert(List<RecommendedItem> itemList) {
        /**
         * 获取Goods ID列表
         */
        List<Long> goodsIdList = getIdList(itemList);
        /**
         * 从数据库获取商品列表
         */
        List<Goods> goodsList = goodsService.listByIdList(goodsIdList);
        return getRecommendGoodsDto(itemList, goodsList);
    }

    /**
     * 获取商品ID列表
     *
     * @param itemList
     * @return
     */
    private List<Long> getIdList(List<RecommendedItem> itemList) {
        List<Long> result = Lists.newArrayList();
        for (RecommendedItem item : itemList) {
            result.add(item.getItemID());
        }
        return result;
    }

    /**
     * 获取推荐商品DTO
     *
     * @param itemList  推荐项列表
     * @param goodsList 商品列表
     * @return
     */
    private List<RecommendGoodsDto> getRecommendGoodsDto(List<RecommendedItem> itemList, List<Goods> goodsList) {
        List<RecommendGoodsDto> result = Lists.newArrayList();
        for (Goods goods : goodsList) {
            RecommendGoodsDto goodsDto = new RecommendGoodsDto();
            goodsDto.setGoods(goods);
            for (RecommendedItem item : itemList) {
                if (item.getItemID() == goodsDto.getGoods().getId().longValue()) {
                    goodsDto.setScore(item.getValue());
                    break;
                }
            }
            result.add(goodsDto);
        }
        return result;
    }

}
