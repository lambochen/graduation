package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 商品一级目录
 * @Author chenlinghong
 * @Date 2019/3/24 18:46
 **/
public interface GoodsCatalogOneDao {

    /**
     * 新增一级目录
     *
     * @param catalogOne
     * @return
     */
    int insert(GoodsCatalogOne catalogOne);

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
    GoodsCatalogOne getById(int id);

    /**
     * 分页获取
     *
     * @param offset 偏移量
     * @param rows   行数
     * @return
     */
    List<GoodsCatalogOne> listAll(@Param("offset") int offset, @Param("rows") int rows);

    /**
     * 统计所有行数
     *
     * @return
     */
    int count();

    /**
     * 获取一级目录所有列表
     *
     * @return
     */
    List<GoodsCatalogOne> listAllGoodsCatalogOne();
}
