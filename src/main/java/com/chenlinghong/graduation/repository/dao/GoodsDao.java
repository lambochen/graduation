package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 商品ＤＡＯ
 * @Author chenlinghong
 * @Date 2019/3/30 11:32
 **/
public interface GoodsDao {

    /**
     * 新增商品信息
     *
     * @param goods
     * @return
     */
    int insert(Goods goods);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    Goods getById(long id);

    /**
     * 分页获取所有记录
     *
     * @param offset 偏移量
     * @param rows   每页条数
     * @return
     */
    List<Goods> listAll(@Param("offset") long offset, @Param("rows") long rows);

    /**
     * 获取总记录数
     *
     * @return
     */
    long count();

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
     * 更改基本信息，具体字段见SQL
     *
     * @param goods
     * @return
     */
    int update(Goods goods);

    /**
     * 更新价格
     *
     * @param price
     * @param id
     * @return
     */
    long updatePrice(@Param("price") double price, @Param("id") long id);

    /**
     * 根据商品一级目录ID获取商品列表
     *
     * @param goodsCatalogOneId
     * @return
     */
    List<Goods> listByGoodsCatalogOneId(@Param("goodsCatalogOneId") long goodsCatalogOneId);


    /**
     * 根据商品二级目录ID获取商品列表
     *
     * @param goodsCatalogTwoId
     * @return
     */
    List<Goods> listByGoodsCatalogTwoId(@Param("goodsCatalogTwoId") long goodsCatalogTwoId);

    /**
     * 通过商品id查找商品详情
     *
     * @param id
     * @return
     */
    Goods getGoodsById(@Param("id") long id);
}
