package com.chenlinghong.graduation.recommender.season;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.GraduationRecommender;
import com.chenlinghong.graduation.recommender.data.GraduationRecommendItem;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 基于时令推荐
 * @Author chenlinghong
 * @Date 2019/5/1 23:18
 * @Version V1.0
 */
@Slf4j
@Service
public class SeasonBasedRecommender implements GraduationRecommender {

    @Autowired
    private GoodsCatalogService goodsCatalogService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 无参构造
     */
    public SeasonBasedRecommender() {

    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId) throws TasteException {
        return recommendGraduation(userId, NumericConstant.TEN);
    }

    @Override
    public List<GraduationRecommendItem> recommendGraduation(long userId, int recommendNum) throws TasteException {
        /**
         * 获取时令
         */
        int currentSeason = getCurrentSeason();
        /**
         * 获取当前时令种类
         */
        PageDto<GoodsCatalogTwo> goodsCatalogTwoList = goodsCatalogService.listBySeason(currentSeason);

        return null;
    }

    /**
     * 获取当前时令,
     *
     * @return 1-12表示具体月份
     */
    private int getCurrentSeason() {
        return 0;
    }

}
