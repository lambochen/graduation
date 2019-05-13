package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.BusinessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 店铺基本信息DAO
 * @Author chenlinghong
 * @Date 2019/3/30 17:19
 **/
public interface BusinessInfoDao extends IBaseDao<BusinessInfo> {

    /**
     * 根据用户ID获取所有记录
     *
     * @param userId
     * @param offset
     * @param rows
     * @return
     */
    List<BusinessInfo> listByUser(@Param("userId") long userId,
                                  @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据用户ID获取记录数
     *
     * @param userId
     * @return
     */
    long countByUser(@Param("userId") long userId);


}
