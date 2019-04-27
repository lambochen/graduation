package com.chenlinghong.graduation.microscope.sniffer.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.GoodsCommentScoreEnum;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.microscope.sniffer.UserGoodsBehaviorSniffer;
import com.chenlinghong.graduation.microscope.sniffer.util.UserBehaviorUtil;
import com.chenlinghong.graduation.microscope.util.SessionUtil;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.service.UserBehaviorService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 用户-商品-嗅探器 实现类
 * @Author chenlinghong
 * @Date 2019/4/25 22:50
 * @Version V1.0
 */
@Slf4j
@Service
public class UserGoodsBehaviorSnifferImpl implements UserGoodsBehaviorSniffer {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserBehaviorService userBehaviorService;

    @Override
    public boolean common(UserBehavior userBehavior) {
        if (userBehavior == null) {
            log.error("UserGoodsSniffer#common: param is null.");
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = userBehaviorService.insert(userBehavior);
        /**
         * TODO 处理结果
         */
        if (result != NumericConstant.ONE) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

    @Override
    public boolean common(List<UserBehavior> userBehaviorList) {
        if (userBehaviorList == null || userBehaviorList.size() <= 0) {
            log.error("UserGoodsSniffer#common: param is null. userBehaviorList={}. ", userBehaviorList);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = userBehaviorService.insert(userBehaviorList);
        /**
         * TODO 处理结果
         */
        if (result != userBehaviorList.size()) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

    /**
     * 商品浏览
     *
     * @param goodsId
     * @param request
     */
    @Override
    public boolean click(long goodsId, HttpServletRequest request) {
        if (goodsId <= 0) {
            log.error("UserGoodsSniffer#click: goodsId is illegal. goodsId={}, requst={}. ", goodsId, request);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserId(request);
        if (userId <= 0) {
            // 用户未登录，暂不做任何处理
            log.error("UserGoodsSniffer#click: user not logged in. goodsId={}, requst={}. ", goodsId, request);
            /**
             * TODO 其它处理
             */
            throw new AsyncBusinessException(ErrorEnum.NO_USER);
        }
        // 记录用户浏览该商品记录
        int insertResult = userBehaviorService.insert(goodsId, userId, UserBehaviorEnum.CLICK);
        /**
         * TODO 后期对返回结果处理
         */
        if (insertResult != NumericConstant.ONE) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

    @Override
    public boolean click(UserBehavior behavior) {
        return common(behavior);
    }


    /**
     * 添加购物车
     *
     * @param goodsId
     * @param request
     */
    @Override
    public boolean addToShoppingCart(long goodsId, HttpServletRequest request) {
        if (goodsId <= 0) {
            log.error("UserGoodsSniffer#addToShoppingCart: goodsId is illegal. goodsId={}, requst={}. ", goodsId, request);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserId(request);
        if (userId <= 0) {
            // 用户未登录，暂不做任何处理
            log.error("UserGoodsSniffer#addToShoppingCart: user not logged in. goodsId={}, requst={}. ", goodsId, request);
            /**
             * TODO 其它处理
             */
            throw new AsyncBusinessException(ErrorEnum.NO_USER);
        }
        // 记录用户添加购物车行为
        int insertResult = userBehaviorService.insert(goodsId, userId, UserBehaviorEnum.ADD_TO_CART);
        /**
         * TODO 后期对返回结果处理
         */
        if (insertResult != NumericConstant.ONE) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

    @Override
    public boolean addToShoppingCart(UserBehavior behavior) {
        return common(behavior);
    }

    /**
     * 购买行为
     *
     * @param goodsOrder
     */
    @Override
    public boolean purchase(GoodsOrder goodsOrder) {
        return purchase(goodsOrder.getUserId(), goodsOrder.getGoodsId(), goodsOrder.getNumber());
    }

    /**
     * 购买行为
     *
     * @param userId
     * @param goodsId
     */
    @Override
    public boolean purchase(long userId, long goodsId, int frequency) {
        if (userId <= 0 || goodsId <= 0 || frequency <= 0) {
            // 参数非法
            log.error("UserGoodsSniffer#purchase: param is illegal. userId={}, goodsId={}, frequency={}. ",
                    userId, goodsId, frequency);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        // 记录用户添加购物车行为
        /**
         * TODO 需要进行批量插入
         */
        int insertResult = userBehaviorService.insert(goodsId, userId, UserBehaviorEnum.PURCHASE, frequency);
        /**
         * TODO 后期对返回结果处理
         */
        if (insertResult != frequency) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

    @Override
    public boolean purchase(UserBehavior behavior) {
        return common(behavior);
    }

    /**
     * 评论
     *
     * @param goodsComment
     */
    @Override
    public boolean comment(GoodsComment goodsComment) {
        if (goodsComment == null) {
            log.error("UserGoodsSniffer#comment: param is null.");
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return comment(goodsComment.getUserId(), goodsComment.getGoodsId(), goodsComment.getScore());
    }

    @Override
    public boolean comment(long userId, long goodsId, int score) {
        if (userId <= 0 || goodsId <= 0 || score <= 0) {
            log.error("UserGoodsSniffer#comment: param is illegal. userId={}, goodsId={}, score={}. ",
                    userId, goodsId, score);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        /**
         * 校验评分合法性
         */
        UserBehaviorEnum behaviorEnum = UserBehaviorUtil.getByCommentScore(score);
        if (behaviorEnum == null) {
            // 评分不合法
            log.error("UserGoodsSniffer#comment: param is illegal. userId={}, goodsId={}, score={}. ",
                    userId, goodsId, score);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        int insertResult = userBehaviorService.insert(goodsId, userId, behaviorEnum.getCode());
        if (insertResult != NumericConstant.ONE) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

    @Override
    public boolean comment(long userId, long goodsId, GoodsCommentScoreEnum scoreEnum) {
        return comment(userId, goodsId, scoreEnum.getScore());
    }

    @Override
    public boolean comment(UserBehavior behavior) {
        return common(behavior);
    }


    @Override
    public boolean search(PageDto<Goods> goodsPageDto, HttpServletRequest request) {
        if (goodsPageDto == null) {
            log.error("UserGoodsSniffer#search: param is null, request={}.", request);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return search(goodsPageDto.getData(), request);
    }

    @Override
    public boolean search(List<Goods> goodsList, HttpServletRequest request) {
        if (goodsList == null || goodsList.size() <= 0) {
            log.error("UserGoodsSniffer#search: param is null. goodsList={}, request={}. ", goodsList, request);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        long userId = sessionUtil.getUserId(request);
        if (userId <= 0) {
            // 获取失败，用户尚未登录
            log.error("UserGoodsSniffer#search: user is not logged in. goodsList={}, request={}. ", goodsList, request);
            throw new AsyncBusinessException(ErrorEnum.NO_USER);
        }
        List<UserBehavior> behaviorList = Lists.newArrayList();
        for (Goods goods : goodsList) {
            UserBehavior behavior = new UserBehavior(userId, goods.getId().longValue(), UserBehaviorEnum.SEARCH.getCode());
            behaviorList.add(behavior);
        }
        return search(behaviorList);
    }

    @Override
    public boolean search(List<UserBehavior> userBehaviorList) {
        if (userBehaviorList == null || userBehaviorList.size() <= 0) {
            log.error("UserGoodsSniffer#search: param is null. userBehaviorList={}. ", userBehaviorList);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int insertResult = userBehaviorService.insert(userBehaviorList);
        if (insertResult != NumericConstant.ONE) {
            /**
             * TODO 处理异常
             */
            return false;
        }
        return true;
    }

}
