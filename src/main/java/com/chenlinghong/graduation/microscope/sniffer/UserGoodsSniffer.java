package com.chenlinghong.graduation.microscope.sniffer;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.microscope.util.SessionUtil;
import com.chenlinghong.graduation.service.UserBehaviorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 浏览行为 嗅探器
 * @Author chenlinghong
 * @Date 2019/4/25 11:00
 * @Version V1.0
 */
@Slf4j(topic = "me")
@Service
public class UserGoodsSniffer {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserBehaviorService userBehaviorService;

    /**
     * 商品浏览
     *
     * @param goodsId
     * @param request
     */
    @Async(value = AsyncNameConstant.MICROSCOPE)
    public void click(long goodsId, HttpServletRequest request) {
        if (goodsId <= 0) {
            log.error("BrowseSniffer#click: goodsId is illegal. goodsId={}, requst={}. ", goodsId, request);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserId(request);
        if (userId <= 0) {
            // 用户未登录，暂不做任何处理
            log.error("BrowseSniffer#click: user not logged in. goodsId={}, requst={}. ", goodsId, request);
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
    }


    /**
     * 添加购物车
     *
     * @param goodsId
     * @param request
     */
    @Async(value = AsyncNameConstant.MICROSCOPE)
    public void addToShoppingCart(long goodsId, HttpServletRequest request) {
        if (goodsId <= 0) {
            log.error("BrowseSniffer#addToShoppingCart: goodsId is illegal. goodsId={}, requst={}. ", goodsId, request);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserId(request);
        if (userId <= 0) {
            // 用户未登录，暂不做任何处理
            log.error("BrowseSniffer#addToShoppingCart: user not logged in. goodsId={}, requst={}. ", goodsId, request);
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
    }


}
