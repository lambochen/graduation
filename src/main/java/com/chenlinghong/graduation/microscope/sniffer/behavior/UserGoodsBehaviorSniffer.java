package com.chenlinghong.graduation.microscope.sniffer.behavior;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.enums.GoodsCommentScoreEnum;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 浏览行为 嗅探器
 * @Author chenlinghong
 * @Date 2019/4/25 11:00
 * @Version V1.0
 */
@Async(value = AsyncNameConstant.MICROSCOPE)
public interface UserGoodsBehaviorSniffer extends BehaviorSniffer {

    /**
     * 添加单个用户行为记录
     *
     * @param userBehavior
     */
    Boolean common(UserBehavior userBehavior);

    /**
     * 添加多个用户行为记录
     *
     * @param userBehaviorList
     */
    Boolean common(List<UserBehavior> userBehaviorList);

    /**
     * 点击行为
     *
     * @param goodsId
     * @param request
     */
    Boolean click(long goodsId, HttpServletRequest request);

    /**
     * 点击行为
     *
     * @param behavior
     */
    Boolean click(UserBehavior behavior);

    /**
     * 添加购物车
     *
     * @param goodsId
     * @param request
     */
    Boolean addToShoppingCart(long goodsId, HttpServletRequest request);

    /**
     * 添加购物车
     *
     * @param behavior
     */
    Boolean addToShoppingCart(UserBehavior behavior);

    /**
     * 购买
     *
     * @param goodsOrder
     */
    Boolean purchase(GoodsOrder goodsOrder);

    /**
     * 购买
     *
     * @param userId
     * @param goodsId
     * @param frequency 购买数量
     */
    Boolean purchase(long userId, long goodsId, int frequency);

    /**
     * 购买
     *
     * @param behavior
     */
    Boolean purchase(UserBehavior behavior);

    /**
     * 评论
     *
     * @param goodsComment 商品评论对象
     */
    Boolean comment(GoodsComment goodsComment);

    /**
     * 评论
     *
     * @param userId
     * @param goodsId
     * @param score   评分
     */
    Boolean comment(long userId, long goodsId, int score);

    /**
     * 评论
     *
     * @param userId
     * @param goodsId
     * @param scoreEnum 评分枚举
     */
    Boolean comment(long userId, long goodsId, GoodsCommentScoreEnum scoreEnum);

    /**
     * 评论
     *
     * @param behavior
     */
    Boolean comment(UserBehavior behavior);

    /**
     * 搜索
     *
     * @param goodsPageDto
     */
    Boolean search(PageDto<Goods> goodsPageDto, HttpServletRequest request);

    /**
     * 搜索
     *
     * @param goodsList
     */
    Boolean search(List<Goods> goodsList, HttpServletRequest request);

    /**
     * 搜索
     *
     * @param userBehaviorList
     */
    Boolean search(List<UserBehavior> userBehaviorList);

}
