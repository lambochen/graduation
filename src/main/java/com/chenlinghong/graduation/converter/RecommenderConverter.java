package com.chenlinghong.graduation.converter;

import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.GoodsService;
import com.google.common.collect.Lists;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 推荐系统转换器
 * @Author chenlinghong
 * @Date 2019/4/30 23:08
 * @Version V1.0
 */
@Service(value = "recommenderConverter")
public class RecommenderConverter implements Converter {

    @Autowired
    private GoodsService goodsService;

    /**
     * 由推荐Item转换为Goods列表
     *
     * @param itemList  推荐系统推荐结果
     * @return
     */
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
