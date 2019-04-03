package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户基本信息
 * @Author chenlinghong
 * @Date 2019/4/3 15:31
 **/
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过密码登录
     *
     * @param telephone
     * @param password
     * @param request
     * @return
     */
    @PostMapping(value = "/login/pwd")
    public ResultVo loginByPwd(String telephone, String password, HttpServletRequest request) {
        log.info("UserController#loginByPwd: parameter info. telephone={}, password={}", telephone, password);
        if (StringUtils.isBlank(telephone) || StringUtils.isBlank(password)) {
            // 参数为空
            log.error("UserController#loginByPwd: param is null. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 写入手机号
        SessionUtil.putTelephone(telephone, request);
        return ResultUtil.success(userService.loginByPwd(telephone, password));
    }

    /**
     * 通过短信验证码登录
     *
     * @param smsCode
     * @param request
     * @return
     */
    @PostMapping(value = "/login/sms")
    public ResultVo loginBySms(String smsCode, HttpServletRequest request) {
        log.info("UserController#loginBySms: parameter info. smsCode={}", smsCode);
        if (StringUtils.isBlank(smsCode)) {
            // 参数为空
            log.error("UserController#loginBySms: parameter is null. smsCode={}", smsCode);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        boolean checkResult = SessionUtil.checkSmsCode(smsCode, request);
        if (checkResult == true) {
            // 验证成功
            String telephone = SessionUtil.getTelephone(request);
            return ResultUtil.success(userService.loginBySms(telephone));
        }
        return ResultUtil.error(ErrorEnum.SMS_TIMEOUT);
    }


}
