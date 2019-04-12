package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 商品二级目录
 * @Author chenlinghong
 * @Date 2019/3/24 18:46
 **/
public interface GoodsCatalogTwoDao {

    /**
     * 新增一级目录
     *
     * @param catalogTwo
     * @return
     */
    int insert(GoodsCatalogTwo catalogTwo);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(int id);


    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    GoodsCatalogTwo getById(int id);

    /**
     * 分页获取
     *
     * @param offset 偏移量
     * @param rows   行数
     * @return
     */
    List<GoodsCatalogTwo> listAll(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 统计所有行数
     *
     * @return
     */
    int count();

    /**
     * 根据一级目录ID分页获取二级目录
     *
     * @param catalogOneId
     * @param offset
     * @param rows
     * @return
     */
    List<GoodsCatalogTwo> listByCatalogOne(@Param("catalogOneId") int catalogOneId,
                                           @Param("offset") int offset, @Param("rows") int rows);

    /**
     * 根据一级目录ID获取行数
     *
     * @param catalogOneId
     * @return
     */
    int countByCatalogOne(@Param("catalogOneId") int catalogOneId);

}
