package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.repository.domain.User;

/**
 * @Description 用户基本信息
 * @Author chenlinghong
 * @Date 2019/4/2 21:37
 **/
public interface UserService extends IBaseService<User> {

    /**
     * 注册用户
     *
     * @param user
     */
    void register(User user);


    /**
     * 根据密码登录
     *
     * @param telephone
     * @param password
     * @return
     */
    UserVo loginByPwd(String telephone, String password);

    /**
     * 根据短信验证码登录
     *
     * @param telephone
     * @return
     */
    UserVo loginBySms(String telephone);


}
