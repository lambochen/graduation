package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品二级目录
 * @Author chenlinghong
 * @Date 2019/3/24 18:46
 **/
public interface GoodsCatalogTwoDao extends IBaseDao<GoodsCatalogTwo> {

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

    /**
     * 根据时令获取列表
     *
     * @param season
     * @return
     */
    List<GoodsCatalogTwo> listBySeason(int season);
}
