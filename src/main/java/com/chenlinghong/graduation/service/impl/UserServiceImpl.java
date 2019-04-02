package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.UserDao;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户基本信息
 * @Author chenlinghong
 * @Date 2019/4/2 21:55
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {

    }

    @Override
    public UserVo loginByPwd(String telephone, String password) {
        return null;
    }

    @Override
    public UserVo loginBySms(String telephone) {
        return null;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public PageDto<User> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }
}
