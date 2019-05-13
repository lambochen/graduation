package com.chenlinghong.graduation.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户更新数据视图对象
 * @Author chenlinghong
 * @Date 2019/4/14 17:17
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateVo implements Serializable {

    private static final long serialVersionUID = 9217878056654350625L;

    /**
     * 用户昵称
     */
    @NotNull(message = "用户昵称不能为空")
    private String nickName;

    /**
     * 真实姓名
     */
    // @NotNull(message = "真实姓名不能为空")
    private String realName;

    /**
     * 性别（0：保密 1：男 2：女）
     */
    // @NotNull(message = "性别不能为空")
    private Integer gender;

    /**
     * 出生日期
     */
    // @NotNull(message = "出生日期不能为空")
    private Date birthday;

    /**
     * 国家
     */
    // @NotNull(message = "国家信息不能为空")
    private String country;

    /**
     * 省份
     */
    // @NotNull(message = "省份信息不能为空")
    private String province;

    /**
     * 城市
     */
    // @NotNull(message = "城市信息不能为空")
    private String city;

    /**
     * 详细地址
     */
    // @NotNull(message = "详细地址不能为空")
    private String position;

    /**
     * 经度
     */
    // @NotNull(message = "经度不能为空")
    private String latitude;

    /**
     * 纬度
     */
    // @NotNull(message = "纬度不能为空")
    private String longitude;

    /**
     * 描述
     */
    // @NotNull(message = "描述信息不能为空")
    private String description;
}
