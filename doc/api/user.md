

## 用户信息

#### 一、注册用户

    POST /graduation/user/register
    
参数

    nickName [string] : 用户昵称
    realName [string] : 真实姓名
    telephone [string] : 电话号码
    password [string] : 密码
    country [string] : 国家
    province [string] : 省份
    city [string] : 城市
    position [string] : 详细地址
    latitude [string] : 经度
    longitude [string] : 纬度
    avatarUrl [string] : 头像URL
    description [string] : 描述
    gender [int] : 性别（0：保密 1：男 2：女）
    birthday [date] : 出生日期
    
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
