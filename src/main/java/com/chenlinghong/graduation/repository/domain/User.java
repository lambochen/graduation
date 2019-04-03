package com.chenlinghong.graduation.repository.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description 用户基本信息
 * @Author chenlinghong
 * @Date 2019/3/24 17:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDomain {

    private static final long serialVersionUID = 4044277484420982044L;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 性别（0：保密 1：男 2：女）
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 详细地址
     */
    private String position;

    /**
     * 经度
     */
    private String latitude;

    /**
     * 纬度
     */
    private String longitude;

    /**
     * 用户头像URL
     */
    private String avatarUrl;

    /**
     * 用户类型（0：普通用户 1：商家 2：管理员 3：超级管理员）
     */
    private Integer type;

    /**
     * 描述
     */
    private String description;

    public User(String telephone){
        this.telephone = telephone;
        this.gender = 0;
        this.type = 0;
    }
}
