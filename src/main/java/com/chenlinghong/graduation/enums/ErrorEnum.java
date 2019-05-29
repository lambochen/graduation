package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 错误码枚举类
 * @Author chenlinghong
 * @Date 2019/3/13 21:04
 **/
@Getter
public enum ErrorEnum {

    SUCCESS(0, "请求成功"),


    /**
     * 1XXX 短信验证码相关
     */
    SMS_SEND_ERROR(1000, "发送短信验证码失败"),
    NO_SMS(1001, "短信验证码不存在，请先发送短信验证码。。。"),
    SMS_TIMEOUT(1002, "短信验证码无效,请重新获取"),
    SMS_ERROR(1003, "短信验证码不正确"),

    /**
     * 2XXX 其他错误
     */
    PARAM_IS_NULL(2000, "参数为空"),
    PARAM_ILLEGAL(2001, "参数非法"),
    TELEPHONE_ILLEGAL(2002, "电话号码非法"),
    SERVER_ERROR(2222, "服务器未知错误"),
    PARAM_ERROR(2003, "参数错误"),
    FILE_HANDLE_ERROR(2004, "文件处理异常"),
    FILE_IS_NULL(2005, "文件不存在"),
    FILE_STREAM_CREATE_ERROR(2006, "文件流创建错误"),
    UNKNOWN_ERROR(2007, "未知错误"),
    DELETE_ERROR(2008, "删除失败"),

    /**
     * 3XXX 用户相关错误
     */
    NO_USER(3000, "用户不存在或未登录"),
    PASSWORD_IS_ERROR(3001, "密码不正确"),
    INSERT_USER_ERROR(3002, "新增用户失败"),
    UPDATE_USER_ERROR(3003, "更新用户信息失败"),
    INSERT_USER_TAG_ERROR(3004, "新增用户标签失败"),
    UPDATE_USER_TAG_ERROR(3005, "更新用户标签失败"),


    /**
     * 4XXX session相关
     */
    SESSION_DATA_IS_NULL(4000, "Session数据为空"),


    /**
     * 5XXX 消息相关
     */
    CHAT_INSERT_ERROR(5000, "消息写入数据库失败"),


    /**
     * 6XXX 商品相关
     */
    COMMENT_INSERT_ERROR(6000, "添加评论失败"),
    FAILED_TO_INSERT_SHOPPING_CART(6001, "添加购物车失败"),
    NO_GOODS(6002, "商品不存在"),


    /**
     * 7XXX 用户行为相关
     */
    ERROR_TO_INSERT_USER_BEHAVIOR(7000, "新增用户行为失败"),
    BEHAVIOR_DATA_NOT_EXISTS(7001, "用户行为数据不存在"),

    /**
     * 8XXX 用户偏好相关
     */
    USER_PREFERENCE_NOT_EXISTS(8000, "用户偏好信息不存在"),

    /**
     * 9XXX 评估
     */
    EVALUTOR_FAILED_INSERT(9000, "评估数据写入失败"),


    ;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
