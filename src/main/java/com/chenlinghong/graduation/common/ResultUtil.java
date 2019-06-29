package com.chenlinghong.graduation.common;

import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;

/**
 * @Description API返回数据工具类
 * @Author chenlinghong
 * @Date 2018/12/1 16:26
 **/
public final class ResultUtil {

    public static ResultVo success(Object data) {
        ResultVo result = new ResultVo();
        result.setCode(0);
        result.setMsg("请求成功");
        result.setData(data);
        return result;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo result = new ResultVo();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static ResultVo error(ErrorEnum errorEnum){
        ResultVo result = new ResultVo();
        result.setCode(errorEnum.getCode());
        result.setMsg(errorEnum.getMsg());
        result.setData(null);
        return result;
    }

    public static ResultVo error(BusinessException e){
        ResultVo result = new ResultVo();
        result.setCode(e.getCode());
        result.setMsg(e.getMessage());
        result.setData(null);
        return result;
    }

}
