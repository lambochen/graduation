package com.chenlinghong.graduation.recommender.season;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.AbstractGraduationRecommender;
import com.chenlinghong.graduation.recommender.GraduationRecommender;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description 时令推荐
 * @Author chenlinghong
 * @Date 2019/5/2 15:56
 * @Version V1.0
 */
@Service
@Slf4j
public class SeasonBasedRecommender extends AbstractGraduationRecommender implements GraduationRecommender {

    @Autowired
    private GoodsCatalogService goodsCatalogService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public RecommendDto<RecommendGoodsDto> recommend() {
        return recommend(NumericConstant.TEN);
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(int recommendNum) {
        return recommend(NumericConstant.ONE, (long) recommendNum);
    }

    /**
     * 分页获取
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public RecommendDto<RecommendGoodsDto> recommend(long pageNo, long pageSize) {
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
                goodsService.listByCatalogTwoList(goodsCatalogTwoList.getData(), pageNo, pageSize);
        return converter(goodsPageDto);
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(long userId) {
        return null;
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(long userId, int recommendNum) {
        return null;
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
