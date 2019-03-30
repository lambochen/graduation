package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.Goods;

/**
 * @Description 商品ＤＡＯ
 * @Author chenlinghong
 * @Date 2019/3/30 11:32
 **/
public interface GoodsDao {

    /**
     * 新增商品信息
     * @param goods
     * @return
     */
    int insert(Goods goods);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Goods getById(int id);



}
