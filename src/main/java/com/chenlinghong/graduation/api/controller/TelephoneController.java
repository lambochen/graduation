package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.constant.SessionConstant;
import com.chenlinghong.graduation.util.TelephoneUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description 电话号码
 * @Author chenlinghong
 * @Date 2019/4/3 16:05
 **/
@RestController
@RequestMapping(value = "/tel")
public class TelephoneController {

    @GetMapping(value = "/sms")
    public ResultVo sendSms(String telephone, HttpServletRequest request) {
        // 发送短信
        String smsCode = TelephoneUtil.sendSMSCode(telephone);
        // 写入session
        inputTelephone(telephone, request);
        /**
         * TODO 将验证码写入redis
         */
        return ResultUtil.success();
    }

    /**
     * 将电话号码写入session
     *
     * @param telephone
     * @param request
     */
    private void inputTelephone(String telephone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstant.TELEPHONE, telephone);
    }
}
