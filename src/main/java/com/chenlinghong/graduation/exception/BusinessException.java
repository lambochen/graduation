package com.chenlinghong.graduation.exception;

import com.chenlinghong.graduation.enums.ErrorEnum;
import lombok.Getter;

/**
 * @Description 业务异常
 * @Author chenlinghong
 * @Date 2018/12/1 16:37
 **/
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private Integer code;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorEnum.UNKNOWN_ERROR.getCode();
    }

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
