package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.chenlinghong.graduation.util.TelephoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 电话号码
 * @Author chenlinghong
 * @Date 2019/4/3 16:05
 **/
@RestController
@RequestMapping(value = "/tel")
public class TelephoneController {

    @Autowired
    private MyRedisUtil redisUtil;

    @Autowired
    private SessionUtil sessionUtil;

    @GetMapping(value = "/sms")
    public ResultVo sendSms(@RequestParam(value = "telephone") String telephone, HttpServletRequest request) {
        // 发送短信
        String smsCode = TelephoneUtil.sendSMSCode(telephone);
        // 写入session
        sessionUtil.putTelephone(telephone, request);
        /**
         * 将验证码写入redis
         */
        redisUtil.putSmsCode(telephone, smsCode);
        return ResultUtil.success();
    }


}
