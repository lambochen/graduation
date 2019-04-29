package com.chenlinghong.graduation.api.vo;

import com.chenlinghong.graduation.repository.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 用户信息
 * @Author chenlinghong
 * @Date 2019/4/2 21:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {

    /**
     * 用户基本信息
     */
    private User userInfo;

    /**
     * 是否新用户
     */
    private boolean newUser = false;

}
