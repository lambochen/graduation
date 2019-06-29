package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description 店铺基本信息
 * @Author chenlinghong
 * @Date 2019/3/30 17:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessInfo extends BaseDomain {

    private static final long serialVersionUID = 940091860671199648L;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺描述
     */
    private String description;

    /**
     * 开店时间
     */
    private Date openingTime;

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
     * 具体地址
     */
    private String position;

    /**
     * 纬度
     */
    private String longitude;

    /**
     * 经度
     */
    private String latitude;

    /**
     * 店铺头像
     */
    private String avatarUrl;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 用户ID
     */
    private Long userId;
}
