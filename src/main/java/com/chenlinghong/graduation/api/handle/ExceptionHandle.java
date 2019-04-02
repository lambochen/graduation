package com.chenlinghong.graduation.api.handle;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
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

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException baseException = (BusinessException) e;
            return ResultUtil.error(baseException.getCode(), baseException.getMessage());
        } else {
            log.error("【系统异常】{}", e);
            return ResultUtil.error(1, "未知异常");
        }
    }
}
