package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.UserDao;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserService;
import com.chenlinghong.graduation.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        /**
         * TODO 其他业务处理
         */
        insert(user);
    }

    @Override
    public UserVo loginByPwd(String telephone, String password) {
        if (StringUtils.isBlank(telephone) || StringUtils.isBlank(password)) {
            // 参数为空
            log.error("UserService.loginByPassword: param is null. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 校验用户是否存在
        int userCount = userDao.countByTelephone(telephone);
        if (userCount <= 0) {
            // 用户不存在
            log.error("UserService.loginByPassword: no user. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.NO_USER);
        }
        // 获取密码密文
        String passwordMD5 = EncryptionUtil.ccMD5(password);
        // 校验登录
        int resultCount = userDao.countByTelephoneAndPassword(telephone, passwordMD5);
        if (resultCount <= 0) {
            // 密码错误
            log.error("UserService.loginByPassword: password is error. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        } else if (resultCount == 1) {
            // 登录成功
            return getByTelephone(telephone);
        } else {
            // TODO 数据有误
            log.error("UserService.loginByPassword: database has some errors. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        }
    }

    @Override
    public UserVo loginBySms(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            // 参数为空
            log.error("UserService.loginBySms: param is null. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int dataCount = userDao.countByTelephone(telephone);
        if (dataCount <= 0) {
            // 密码错误
            log.error("UserService.loginBySms: password is error. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        } else if (dataCount == 1) {
            // 登录成功
            return getByTelephone(telephone);
        } else {
            // TODO 数据有误
            log.error("UserService.loginBySms: database has some errors. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        }
    }

    @Override
    public int insert(User user) {
        if (user == null) {
            log.error("UserService#insert: param is null. user={}", user);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        // 加密处理
        user.setPassword(EncryptionUtil.ccMD5(user.getPassword()));
        int result = userDao.insert(user);
        /**
         * TODO 后期做校验处理
         */
        return result;
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

    private UserVo getByTelephone(String telephone) {
        UserVo userVo = new UserVo();
        // 用户基本信息
        User userInfo = userDao.getByTelephone(telephone);
        userVo.setUserInfo(userInfo);
        // TODO 其它信息
        return userVo;
    }
}
