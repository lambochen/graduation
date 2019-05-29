package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.api.vo.HomePageVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.service.HomePageService;
import com.chenlinghong.graduation.service.RecommendService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description 首页
 * @Author chenlinghong
 * @Date 2019/4/29 9:14
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "/recommend")
public class RecommendController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private RecommendService recommendService;

    /**
     * 获取首页数据
     *
     * @param request
     * @return
     */
    @GetMapping(value = {"/home"})
    public ResultVo<HomePageVo> home(HttpServletRequest request) throws TasteException {
        long userId = sessionUtil.getUserIdNoCheck(request);
        HomePageVo result = homePageService.get(userId);
        return ResultUtil.success(result);
    }

    /**
     * 根据推荐类型获取
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/recommend/{recommendType}")
    public ResultVo listByType(@PathVariable(value = "recommendType") Integer recommendType,
                               @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                               HttpServletRequest request) throws TasteException {
        /**
         * 热门推荐、时令推荐需单独处理
         */
        RecommendTypeEnum typeEnum = getByCode(recommendType);
        long userId = sessionUtil.getUserIdNoCheck(request);
        if (typeEnum == RecommendTypeEnum.POPULAR_RECOMMEND) {
            /**
             * 热门推荐
             */
            PageDto<RecommendRankingGoods> popularRecommend = recommendService.popularRecommend(pageNo, pageSize);
            return ResultUtil.success(popularRecommend);
        } else if (typeEnum == RecommendTypeEnum.SEASON_RECOMMEND) {
            /**
             * 时令推荐
             */
            PageDto<RecommendQueueGoods> seasonRecommend = recommendService.seasonRecommend(pageNo, pageSize);
            return ResultUtil.success(seasonRecommend);
        }
        PageDto<RecommendQueueGoods> recommendQueueGoodsPageDto = recommendService.recommendByType(typeEnum, userId);
        return ResultUtil.success(recommendQueueGoodsPageDto);
    }

    /**
     * 获取推荐类型
     *
     * @param recommendType
     * @return
     */
    public static RecommendTypeEnum getByCode(int recommendType) {
        RecommendTypeEnum result = null;
        if (recommendType == RecommendTypeEnum.USER_BASED_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.USER_BASED_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.ITEM_BASED_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.ITEM_BASED_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.SLOPE_ONE_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.SLOPE_ONE_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.POPULAR_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.POPULAR_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.SEASON_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.SEASON_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.USER_TAG_BASED_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.USER_TAG_BASED_RECOMMEND;
        } else {
            /**
             * TODO 参数错误
             */
        }
        return result;
    }

}
