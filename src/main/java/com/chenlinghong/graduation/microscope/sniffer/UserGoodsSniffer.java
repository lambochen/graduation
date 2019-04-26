package com.chenlinghong.graduation.microscope.sniffer;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.GoodsCommentScoreEnum;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import com.chenlinghong.graduation.repository.domain.UserBehavior;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 浏览行为 嗅探器
 * @Author chenlinghong
 * @Date 2019/4/25 11:00
 * @Version V1.0
 */
public interface UserGoodsSniffer extends Sniffer {

    /**
     * 添加单个用户行为记录
     *
     * @param userBehavior
     */
    void common(UserBehavior userBehavior);

    /**
     * 添加多个用户行为记录
     *
     * @param userBehaviorList
     */
    void common(List<UserBehavior> userBehaviorList);

    /**
     * 点击行为
     *
     * @param goodsId
     * @param request
     */
    void click(long goodsId, HttpServletRequest request);

    /**
     * 点击行为
     *
     * @param behavior
     */
    void click(UserBehavior behavior);

    /**
     * 添加购物车
     *
     * @param goodsId
     * @param request
     */
    void addToShoppingCart(long goodsId, HttpServletRequest request);

    /**
     * 添加购物车
     *
     * @param behavior
     */
    void addToShoppingCart(UserBehavior behavior);

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
     * 购买
     *
     * @param behavior
     */
    void purchase(UserBehavior behavior);

    /**
     * 评论
     *
     * @param goodsComment 商品评论对象
     */
    void comment(GoodsComment goodsComment);

    /**
     * 评论
     *
     * @param userId
     * @param goodsId
     * @param score   评分
     */
    void comment(long userId, long goodsId, int score);

    /**
     * 评论
     *
     * @param userId
     * @param goodsId
     * @param scoreEnum 评分枚举
     */
    void comment(long userId, long goodsId, GoodsCommentScoreEnum scoreEnum);

    /**
     * 评论
     *
     * @param behavior
     */
    void comment(UserBehavior behavior);

    /**
     * 搜索
     *
     * @param goodsPageDto
     */
    void search(PageDto<Goods> goodsPageDto, HttpServletRequest request);

    /**
     * 搜索
     *
     * @param goodsList
     */
    void search(List<Goods> goodsList, HttpServletRequest request);

    /**
     * 搜索
     *
     * @param userBehaviorList
     */
    void search(List<UserBehavior> userBehaviorList);

}
