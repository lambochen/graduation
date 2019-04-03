package com.chenlinghong.graduation.api.util;

import com.chenlinghong.graduation.constant.SessionConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description session相关操作
 * @Author chenlinghong
 * @Date 2019/4/3 16:26
 **/
@Slf4j
public class SessionUtil {

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
    public static boolean checkSmsCode(String smsCode, HttpServletRequest request) {
        String telephone = getTelephone(request);
        /**
         * TODO 获取redis中的smsCode
         */
        String redisSmsCode = "";
        if (smsCode.equalsIgnoreCase(redisSmsCode)) {
            // 验证成功
            return true;
        }
        return false;
    }
}
