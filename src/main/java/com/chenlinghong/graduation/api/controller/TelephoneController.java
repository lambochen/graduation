package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.common.redis.RedisKeyUtil;
import com.chenlinghong.graduation.common.redis.RedisUtil;
import com.chenlinghong.graduation.util.TelephoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private RedisKeyUtil redisKeyUtil;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "/sms")
    public ResultVo sendSms(String telephone, HttpServletRequest request) {
        // 发送短信
        String smsCode = TelephoneUtil.sendSMSCode(telephone);
        // 写入session
        SessionUtil.putTelephone(telephone, request);
        /**
         * 将验证码写入redis
         */
        // 生成redis key
        String redisKey = redisKeyUtil.generateKeyForSms(telephone);
        // 写入redis
        redisUtil.set(redisKey, smsCode);
        return ResultUtil.success();
    }


}
