package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.repository.domain.User;
import org.springframework.scheduling.annotation.Async;

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

    /**
     * 通过电话号码获取用户基本信息
     *
     * @param telephone
     * @return
     */
    User getUserByTelephone(String telephone);

    /**
     * 根据电话号码获取用户基本信息
     *
     * @param telephone
     * @return
     */
    UserVo getUserVoByTelephone(String telephone);

    /**
     * 根据电话号码获取用户基本信息,不刷新缓存
     *
     * @param telephone
     * @return
     */
    UserVo getUserVoByTelephoneNotPushCache(String telephone);

    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @return
     */
    int updatePassword(long id, String password);

    /**
     * 更改用户头像
     *
     * @param id
     * @param avatarUrl
     * @return
     */
    int updateAvatarUrl(long id, String avatarUrl);

    /**
     * 异步写入缓存
     *
     * @param telephone
     */
    @Async(value = AsyncNameConstant.SERVICE)
    void pushUserVoToCache(String telephone);
}
