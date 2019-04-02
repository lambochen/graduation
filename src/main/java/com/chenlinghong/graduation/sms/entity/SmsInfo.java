package com.chenlinghong.graduation.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description 短信验证码信息
 * @Author chenlinghong
 * @Date 2019/1/24 18:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsInfo {

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 短信验证码
     */
    private String smsCode;

    /**
     * 发送时间
     */
    private Date StartTime;

    /**
     * 时长
     */
    private Long duration;
}
