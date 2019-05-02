package com.chenlinghong.graduation.recommender.season.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.season.SeasonBasedRecommender;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.GoodsService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description 基于时令推荐
 * @Author chenlinghong
 * @Date 2019/5/1 23:18
 * @Version V1.0
 */
@Slf4j
@Service
public class SeasonBasedRecommenderImpl implements SeasonBasedRecommender {

    @Autowired
    private GoodsCatalogService goodsCatalogService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 无参构造
     */
    public SeasonBasedRecommenderImpl() {

    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend() {
        return recommend(NumericConstant.TEN);
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(int recommendNum) {
        /**
         * 获取时令
         */
        int currentSeason = getCurrentSeason();
        /**
         * 获取当前时令种类
         */
        PageDto<GoodsCatalogTwo> goodsCatalogTwoList = goodsCatalogService.listBySeason(currentSeason);
        if (goodsCatalogTwoList == null || goodsCatalogTwoList.getData() == null) {
            log.error("SeasonBasedRecommender#recommend: current season is null.");
            return null;
        }
        PageDto<Goods> goodsPageDto =
                goodsService.listByCatalogTwoList(goodsCatalogTwoList.getData(), NumericConstant.THREE);
        return converter(goodsPageDto);
    }

    /**
     * 数据转换
     *
     * @param goodsPageDto
     * @return
     */
    private RecommendDto<RecommendGoodsDto> converter(PageDto<Goods> goodsPageDto) {
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

    /**
     * 获取当前时令,
     *
     * @return 1-12表示具体月份
     */
    private int getCurrentSeason() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return NumericConstant.ONE + calendar.get(Calendar.MONTH);
    }

}
