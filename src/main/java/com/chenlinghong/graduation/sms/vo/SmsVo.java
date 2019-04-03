package com.chenlinghong.graduation.sms.vo;

import com.chenlinghong.graduation.sms.entity.MiaoDiReturn;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/1/24 17:43
 **/
@Data
@NoArgsConstructor
public class SmsVo {

    /**
     * 状态码
     */
    private String statusCode;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 秒滴返回对象
     */
    private MiaoDiReturn miaoDiReturn;
}
