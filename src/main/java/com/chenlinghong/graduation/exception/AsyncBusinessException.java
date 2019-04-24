package com.chenlinghong.graduation.exception;

import com.chenlinghong.graduation.enums.ErrorEnum;
import lombok.Getter;

/**
 * @Description 异步任务业务异常
 * @Author chenlinghong
 * @Date 2019/4/24 10:49
 * @Version V1.0
 */
@Getter
public class AsyncBusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    public AsyncBusinessException(ErrorEnum errorEnum){
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public AsyncBusinessException(String message, Throwable throwable){
        super(message, throwable);
        this.code = ErrorEnum.UNKNOWN_ERROR.getCode();
    }

}
