package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户基本信息DAO
 * @Author chenlinghong
 * @Date 2019/3/24 17:58
 **/
public interface UserDao {

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return
     */
    int insert(User user);

    /**
     * 根据ID删除用户
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据用户ID获取
     *
     * @param id
     * @return
     */
    User getById(int id);

    /**
     * 更新用户基本信息
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 更新用户密码
     *
     * @param id
     * @param password
     * @return
     */
    int updatePassword(@Param("id") int id, @Param("password") String password);

    /**
     * 更新用户头像URL
     *
     * @param id
     * @param avatarUrl
     * @return
     */
    int updateAvatarUrl(@Param("id") int id, @Param("avatarUrl") String avatarUrl);


}
