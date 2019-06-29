package com.chenlinghong.graduation.recommender.user;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.AbstractGraduationRecommender;
import com.chenlinghong.graduation.recommender.GraduationRecommender;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.repository.domain.UserTag;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.GoodsService;
import com.chenlinghong.graduation.service.UserTagService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 基于用户标签的推荐
 * @Author chenlinghong
 * @Date 2019/5/2 17:52
 * @Version V1.0
 */
@Service
@Slf4j
public class UserTagBasedRecommender extends AbstractGraduationRecommender implements GraduationRecommender {

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public RecommendDto<RecommendGoodsDto> recommend() {
        return null;
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(int recommendNum) {
        return null;
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(long userId) {
        /**
         * 默认推荐10条
         */
        return recommend(userId, NumericConstant.TEN);
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(long userId, int recommendNum) {
        if (userId <= 0 || recommendNum <= 0) {
            log.error("UserTagBasedRecommender#recommend: param is illegal. userId={}, recommendNum={}.",
                    userId, recommendNum);
            return null;
        }
        /**
         * 获取用户标签
         */
        PageDto userTagDto = userTagService.listByUser(userId);
        if (userTagDto == null || userTagDto.getData() == null || userTagDto.getData().size() <= 0) {
            log.error("UserTagBasedRecommender#recommend: failed to get user tag. userId={}, recommendNum={}, " +
                    "userTagDto={}", userId, recommendNum, userTagDto);
            return null;
        }
        List<GoodsCatalogTwo> catalogTwoList = converter(userTagDto.getData());
        PageDto<Goods> goodsPageDto =
                goodsService.listByCatalogTwoList(catalogTwoList, NumericConstant.ONE, NumericConstant.THREE);
        return converter(goodsPageDto, userId);
    }

    /**
     * 转换数据
     *
     * @param userTagList
     * @return
     */
    private List<GoodsCatalogTwo> converter(List<UserTag> userTagList) {
        List<GoodsCatalogTwo> result = Lists.newArrayList();
        for (UserTag userTag : userTagList) {
            GoodsCatalogTwo catalogTwo = new GoodsCatalogTwo();
            catalogTwo.setCatalogOneId(userTag.getGoodsCatalogOneId());
            catalogTwo.setId(userTag.getGoodsCatalogTwoId());
        }
        return result;
    }


}
