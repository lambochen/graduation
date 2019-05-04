package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品ＤＡＯ
 * @Author chenlinghong
 * @Date 2019/3/30 11:32
 **/
public interface GoodsDao extends IBaseDao<Goods> {


    /**
     * 根据一级目录ID分页获取
     *
     * @param catalogOneId 一级目录ID
     * @param offset
     * @param rows
     * @return
     */
    List<Goods> listByCatalogOne(@Param("catalogOneId") int catalogOneId,
                                 @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据一级目录获取总条数
     *
     * @param catalogOneId
     * @return
     */
    long countByCatalogOne(@Param("catalogOneId") int catalogOneId);

    /**
     * 根据二级目录分页获取
     *
     * @param catalogTwoId
     * @param offset
     * @param rows
     * @return
     */
    List<Goods> listByCatalogTwo(@Param("catalogTwoId") int catalogTwoId,
                                 @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据二级目录获取记录数
     *
     * @param catalogTwoId
     * @return
     */
    long countByCatalogTwo(@Param("catalogTwoId") int catalogTwoId);

    /**
     * 根据商户ID分页获取
     *
     * @param businessId
     * @param offset
     * @param rows
     * @return
     */
    List<Goods> listByBusiness(@Param("businessId") long businessId,
                               @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据商户ID获取记录数
     *
     * @param businessId
     * @return
     */
    long countByBusiness(@Param("businessId") long businessId);

    /**
     * 通过名称模糊搜索
     *
     * @param name
     * @param offset
     * @param rows
     * @return
     */
    List<Goods> listByName(@Param("name") String name, @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 通过名称获取记录数
     *
     * @param name
     * @return
     */
    long countByName(@Param("name") String name);


    /**
     * 通过ID列表获取
     *
     * @param goodsIdList
     * @return
     */
    List<Goods> listByIdList(@Param("goodsIdList") List<Long> goodsIdList);

    /**
     * 根据商品ID获取记录数
     *
     * @param goodsId
     * @return
     */
    int countByGoodsId(long goodsId);

    /**
     * 更新价格
     *
     * @param price
     * @param id
     * @return
     */
    long updatePrice(@Param("price") double price, @Param("id") long id);

    /**
     * 通过二级目录列表获取
     *
     * @param catalogTwoList
     * @param offset
     * @param rows
     * @return
     */
    List<Goods> listByCatalogTwoList(@Param("catalogTwoList") List<GoodsCatalogTwo> catalogTwoList,
                                     @Param("offset") long offset, @Param("rows") long rows);
}
