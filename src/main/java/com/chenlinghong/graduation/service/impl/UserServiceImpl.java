package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.constant.FilePathConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.UserDao;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserService;
import com.chenlinghong.graduation.util.EncryptionUtil;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.chenlinghong.graduation.util.TelephoneUtil;
import com.chenlinghong.graduation.util.UsernameUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    private MyRedisUtil redisUtil;

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
            log.error("UserService#loginByPassword: param is null. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 校验用户是否存在
        int userCount = userDao.countByTelephone(telephone);
        if (userCount <= 0) {
            // 用户不存在
            log.error("UserService#loginByPassword: no user. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.NO_USER);
        }
        // 获取密码密文
        String passwordMD5 = EncryptionUtil.ccMD5(password);
        // 校验登录
        int resultCount = userDao.countByTelephoneAndPassword(telephone, passwordMD5);
        if (resultCount <= 0) {
            // 密码错误
            log.error("UserService#loginByPassword: password is error. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        } else if (resultCount == 1) {
            // 登录成功
            return getUserVoByTelephone(telephone);
        } else {
            // TODO 数据有误
            log.error("UserService#loginByPassword: database has some errors. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        }
    }

    @Override
    public UserVo loginBySms(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            // 参数为空
            log.error("UserService#loginBySms: param is null. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int dataCount = userDao.countByTelephone(telephone);
        if (dataCount <= 0) {
            /**
             * 用户不存在, 进行新增用户
             */
            int insertResult = insert(telephone);
            if (insertResult == 1) {
                UserVo userVo = getUserVoByTelephone(telephone);
                // 标记为新用户
                userVo.setNewUser(true);
                return userVo;
            } else {
                // 插入用户数据有误
                log.error("UserService#loginBySms: failed to insert user. telephone={}", telephone);
                throw new BusinessException(ErrorEnum.INSERT_USER_ERROR);
            }
        } else if (dataCount == 1) {
            // 登录成功
            return getUserVoByTelephone(telephone);
        } else {
            // TODO 数据有误
            log.error("UserService#loginBySms: database has some errors. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.PASSWORD_IS_ERROR);
        }
    }

    @Override
    public User getUserByTelephone(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            log.error("UserService#getUserByTelephone: param is null. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        if (TelephoneUtil.isNotPhoneLegal(telephone)) {
            log.error("UserService#getUserByTelephone: telephone is illegal. telephone={}", telephone);
            throw new BusinessException(ErrorEnum.TELEPHONE_ILLEGAL);
        }
        return userDao.getByTelephone(telephone);
    }

    @Override
    public int updatePassword(long id, String password) {
        if (StringUtils.isBlank(password)) {
            log.error("UserService#updatePassword: param is null. id={}, password={}", id, password);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return userDao.updatePassword(id, EncryptionUtil.ccMD5(password));
    }

    @Override
    public int updateAvatarUrl(long id, String avatarUrl) {
        if (StringUtils.isBlank(avatarUrl)) {
            log.error("UserService#updateAvatarUrl: param is null. id={}, avatarUrl={}", id, avatarUrl);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return userDao.updateAvatarUrl(id, avatarUrl);
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

    /**
     * 内部接口，用于登录及注册
     *
     * @param telephone
     * @return
     */
    private int insert(String telephone) {
        String username = UsernameUtil.getRandomUsername(8);
        return userDao.insertByTelephoneAndUsername(telephone, username, FilePathConstant.DEFAULT_AVATAR);
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
        if (user == null) {
            log.error("UserService#update(user): param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = userDao.update(user);
        if (result == 1) {
            /**
             * 更新缓存
             */
            pushUserVoToCache(user.getTelephone());
        }
        return result;
    }

    @Override
    public UserVo getUserVoByTelephone(String telephone) {
        UserVo userVo = getUserVoByTelephoneNotPushCache(telephone);
        /**
         * 写入redis
         * TODO redisKey后期可能会做处理
         */
        String redisKey = redisUtil.put(userVo);
        return userVo;
    }

    @Override
    public UserVo getUserVoByTelephoneNotPushCache(String telephone) {
        UserVo userVo = new UserVo();
        // 用户基本信息
        User userInfo = userDao.getByTelephone(telephone);
        userVo.setUserInfo(userInfo);
        /**
         * TODO 其它信息
         */
        return userVo;
    }

    /**
     * 将用户Vo数据写入缓存
     *
     * @param telephone
     */
    @Override
    @Async(value = AsyncNameConstant.SERVICE)
    public void pushUserVoToCache(String telephone) {
        UserVo userVo = getUserVoByTelephoneNotPushCache(telephone);

        /**
         * 写入redis
         * TODO redisKey后期可能会做处理
         */
        String redisKey = redisUtil.put(userVo);
    }


}
