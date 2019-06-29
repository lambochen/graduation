package com.chenlinghong.graduation.repository.dao;

import lombok.NonNull;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description DAO基础接口
 * @Author chenlinghong
 * @Date 2019/4/23 22:33
 * @Version V1.0
 */
public interface IBaseDao<T> {

    /**
     * 新增记录
     *
     * @param t
     * @return
     */
    int insert(@NonNull T t);

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
    T getById(long id);

    /**
     * 分页获取
     *
     * @param offset 偏移量
     * @param rows   查询行数
     * @return
     */
    List<T> listAll(@Param("offset") long offset, @Param("rows") long rows);

    /**
     * 获取总记录数
     *
     * @return
     */
    long count();

    /**
     * 修改基本信息
     *
     * @param t
     * @return
     */
    int update(@NonNull T t);
}
