package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 测试类
 * @Author chenlinghong
 * @Date 2019/3/13 20:10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestBean extends BaseDomain {

    private static final long serialVersionUID = -3972611242984352938L;

    /**
     * 名称
     */
    private String name;

}
