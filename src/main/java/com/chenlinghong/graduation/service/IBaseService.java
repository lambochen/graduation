package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;

/**
 * @Description 基础Service接口
 * @Author chenlinghong
 * @Date 2019/4/2 21:39
 **/
public interface IBaseService<T> {

    /**
     * 新增
     *
     * @param t
     * @return
     */
    int insert(T t);

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
     * 获取所有
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<T> listAll(long pageNo, long pageSize);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    int update(T t);

}
