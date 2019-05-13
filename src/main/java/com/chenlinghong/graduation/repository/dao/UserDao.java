package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户基本信息DAO
 * @Author chenlinghong
 * @Date 2019/3/24 17:58
 **/
public interface UserDao extends IBaseDao<User> {

    /**
     * 新增用户，仅填充电话号码，其它采用默认值
     *
     * @param telephone
     * @return
     */
    int insertByTelephone(String telephone);

    /**
     * 新增用户
     * @param telephone
     * @param username
     * @param avatarUrl
     * @return
     */
    int insertByTelephoneAndUsername(@Param("telephone") String telephone,
                                     @Param("username") String username,
                                     @Param("avatarUrl") String avatarUrl);

    /**
     * 根据电话号码获取
     *
     * @param telephone
     * @return
     */
    User getByTelephone(String telephone);

    /**
     * 根据电话号码获取记录数
     *
     * @param telephone
     * @return
     */
    int countByTelephone(String telephone);

    /**
     * 根据密码获取记录数
     *
     * @param telephone
     * @param password
     * @return
     */
    int countByTelephoneAndPassword(@Param("telephone") String telephone, @Param("password") String password);

    /**
     * 更新用户密码
     *
     * @param id
     * @param password
     * @return
     */
    int updatePassword(@Param("id") long id, @Param("password") String password);

    /**
     * 更新用户头像URL
     *
     * @param id
     * @param avatarUrl
     * @return
     */
    int updateAvatarUrl(@Param("id") long id, @Param("avatarUrl") String avatarUrl);


}
