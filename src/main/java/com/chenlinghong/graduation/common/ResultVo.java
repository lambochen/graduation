package com.chenlinghong.graduation.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description API返回模板类
 * @Author chenlinghong@g7.com.cn
 * @Date 2018/12/1 16:23
 **/
@Data
@NoArgsConstructor
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 3535016020293872262L;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

}
