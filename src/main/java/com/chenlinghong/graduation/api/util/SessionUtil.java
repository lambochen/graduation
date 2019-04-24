package com.chenlinghong.graduation.api.util;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.constant.SessionConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserService;
import com.chenlinghong.graduation.util.MyRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description session相关操作
 * @Author chenlinghong
 * @Date 2019/4/3 16:26
 **/
@Slf4j
@Service
public class SessionUtil {

    @Autowired
    private MyRedisUtil redisUtil;

    @Autowired
    private UserService userService;

    /**
     * 将电话号码写入session
     *
     * @param telephone
     * @param request
     */
    public static void putTelephone(String telephone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstant.TELEPHONE, telephone);
    }

    /**
     * 获取手机号码
     *
     * @param request
     * @return
     */
    public static String getTelephone(HttpServletRequest request) {
        HttpSession session = getNotNewSession(request);
        String telephone = (String) session.getAttribute(SessionConstant.TELEPHONE);
        return telephone;
    }

    /**
     * 获取session对象
     *
     * @param request
     * @return
     */
    public static HttpSession getNotNewSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            // 新请求
            log.error("SessionUtil#getNotNewSession: session is new.");
            throw new BusinessException(ErrorEnum.SESSION_DATA_IS_NULL);
        }
        return session;
    }

    /**
     * 校验短信验证码
     *
     * @param smsCode
     * @param request
     * @return
     */
    public boolean checkSmsCode(String smsCode, HttpServletRequest request) {
        String telephone = getTelephone(request);
        String redisSmsCode = redisUtil.getSmsCode(telephone);
        if (smsCode.equalsIgnoreCase(redisSmsCode)) {
            // 验证成功
            return true;
        }
        return false;
    }

    /**
     * 是否是有效登录用户
     *
     * @param request 请求对象
     * @return true:有效登录用户
     */
    public boolean isAliveUser(HttpServletRequest request) {
        // 获取电话号码
        String telephone = getTelephone(request);
        return redisUtil.isAliveUser(telephone);
    }

    /**
     * 获取用户视图对象
     *
     * @param request
     * @return
     */
    public UserVo getUserVo(HttpServletRequest request) {
        String telephone = getTelephone(request);
        return redisUtil.getUserVo(telephone);
    }

    /**
     * 获取用户基本信息
     *
     * @param request
     * @return
     */
    public User getUser(HttpServletRequest request) {
        String telephone = getTelephone(request);
        User result = redisUtil.getUserByTelephone(telephone);
        if (result == null){
            // redis中不存在数据，从DB中获取
            result = userService.getUserByTelephone(telephone);
        }
        return result;
    }

    /**
     * 获取用户ID
     *
     * @param request
     * @return
     */
    public long getUserId(HttpServletRequest request) {
        User user = getUser(request);
        if (user == null) {
            throw new BusinessException(ErrorEnum.NO_USER);
        }
        return user.getId();
    }
}
