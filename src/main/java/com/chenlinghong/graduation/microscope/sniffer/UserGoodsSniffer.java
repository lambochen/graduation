package com.chenlinghong.graduation.microscope.sniffer;

import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 浏览行为 嗅探器
 * @Author chenlinghong
 * @Date 2019/4/25 11:00
 * @Version V1.0
 */
public interface UserGoodsSniffer extends Sniffer {

    /**
     * 点击行为
     *
     * @param goodsId
     * @param request
     */
    void click(long goodsId, HttpServletRequest request);

    /**
     * 添加购物车
     *
     * @param goodsId
     * @param request
     */
    void addToShoppingCart(long goodsId, HttpServletRequest request);

    /**
     * 购买
     *
     * @param goodsOrder
     */
    void purchase(GoodsOrder goodsOrder);

    /**
     * 购买
     *
     * @param userId
     * @param goodsId
     * @param frequency 购买数量
     */
    void purchase(long userId, long goodsId, int frequency);

    /**
     * 评论
     *
     * @param goodsComment 商品评论对象
     */
    void comment(GoodsComment goodsComment);
}
