package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsComment;

/**
 * @Description 商品评论
 * @Author chenlinghong
 * @Date 2019/4/15 12:18
 * @Version V1.0
 */
public interface GoodsCommentService extends IBaseService<GoodsComment> {

    /**
     * 根据用户批量删除
     * @param userId
     * @return
     */
    int deleteByUser(long userId);

    /**
     * 根据ID删除，校验身份
     * @param id
     * @param userId
     * @return
     */
    int deleteById(long id, long userId);

    /**
     * 根据用户分页获取
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<GoodsComment> listByUser(long userId, long pageNo, long pageSize);

    /**
     * 根据商品分页获取
     * @param goodsId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<GoodsComment> listByGoods(long goodsId, long pageNo, long pageSize);
}
