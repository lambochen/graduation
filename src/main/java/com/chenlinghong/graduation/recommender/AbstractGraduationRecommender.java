package com.chenlinghong.graduation.recommender;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description 推荐抽象类
 * @Author chenlinghong
 * @Date 2019/5/2 17:59
 * @Version V1.0
 */
public abstract class AbstractGraduationRecommender implements GraduationRecommender {

    /**
     * 数据转换
     *
     * @param goodsPageDto
     * @param userId
     * @return
     */
    protected RecommendDto<RecommendGoodsDto> converter(PageDto<Goods> goodsPageDto, long userId) {
        RecommendDto<RecommendGoodsDto> result = converter(goodsPageDto);
        result.setUserId(userId);
        return result;
    }

    /**
     * 数据转换
     *
     * @param goodsPageDto
     * @return
     */
    protected RecommendDto<RecommendGoodsDto> converter(PageDto<Goods> goodsPageDto) {
        RecommendDto<RecommendGoodsDto> result = new RecommendDto<>();
        List<RecommendGoodsDto> data = Lists.newArrayList();
        if (goodsPageDto != null) {
            for (Goods goods : goodsPageDto.getData()) {
                RecommendGoodsDto item = new RecommendGoodsDto();
                item.setGoods(goods);
                data.add(item);
            }
        }
        result.setData(new PageDto<>(data));
        return result;
    }

}
