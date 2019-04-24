package com.chenlinghong.graduation.api.handle;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 全局异常处理器
 * @Author chenlinghong
 * @Date 2018/12/1 16:35
 **/
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultVo handle(BusinessException e) {
        log.error("ExceptionHandle#handle(BusinessException) : code={}, msg={}, e={}",
                e.getCode(), e.getMessage(), e);
        return ResultUtil.error(e);
    }

    @ExceptionHandler(value = AsyncBusinessException.class)
    @ResponseBody
    public void handle(AsyncBusinessException e){
        /**
         * TODO 异步方法的异常，暂时做粗粒度的处理，直接打印日志
         */
        log.error("ExceptionHandle#handle(AsyncBusinessException) : code={}, msg={}, e={}",
                e.getCode(), e.getMessage(), e);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo handle(Exception e) {
        log.error("【系统异常】{}", e);
        return ResultUtil.error(1, "未知异常");
    }
}
