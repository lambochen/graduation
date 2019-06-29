package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品评论DAO
 * @Author chenlinghong
 * @Date 2019/4/2 17:08
 **/
public interface GoodsCommentDao extends IBaseDao<GoodsComment> {


    /**
     * 根据用户ID批量删除
     *
     * @param userId
     * @return
     */
    int deleteByUser(long userId);

    /**
     * 根据商品ID批量删除
     *
     * @param goodsId
     * @return
     */
    int deleteByGoods(long goodsId);


    /**
     * 根据用户分页获取
     *
     * @param userId
     * @param offset
     * @param rows
     * @return
     */
    List<GoodsComment> listByUser(@Param("userId") long userId,
                                  @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据用户获取记录行数
     *
     * @param userId
     * @return
     */
    long countByUser(long userId);

    /**
     * 根据商品分页获取
     *
     * @param goodsId
     * @param offset
     * @param rows
     * @return
     */
    List<GoodsComment> listByGoods(@Param("goodsId") long goodsId,
                                   @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据商品获取记录数
     *
     * @param goodsId
     * @return
     */
    long countByGoods(long goodsId);

}
