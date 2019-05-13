

## 用户信息

#### 一、注册用户【已遗弃该接口】

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
    

#### 二、根据密码登录

    POST /graduation/user/login/pwd
    
参数

    telephone [string] : 电话号码 【必填】
    password  [string] : 密码 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "userInfo": {
                "id": 3,
                "gmtCreate": 1554282432000,
                "gmtModified": 1556096564000,
                "nickName": "chenglinghong",
                "realName": "chenglinghong",
                "telephone": "13008142306",
                "gender": 1,
                "birthday": 1556035200000,
                "country": "China",
                "province": "sichuan",
                "city": "chegndu",
                "position": "xhu",
                "latitude": "10.10",
                "longitude": "34.00",
                "avatarUrl": "http://api.xhunccd.top/graduation/file/download?file=D:/data/app/graduation/file/user/20190414/1555251226080_833.jpg",
                "type": 0,
                "description": "test"
            },
            "newUser": false        // 用户是否为新用户
        }
    }
    
返回说明

- userInfo: 用户基本信息
- newUser: 用户是否为新用户。如果是新用户，需要进行下一步选择用户标签的操作。不是新用户则不需要
    
#### 三、根据短信验证码登录【用户不存在时即注册新用户】

    POST /graduation/user/login/sms
    
参数

    smsCode [string] : 短信验证码 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "userInfo": {
                "id": 3,
                "gmtCreate": 1554282432000,
                "gmtModified": 1556096564000,
                "nickName": "chenglinghong",
                "realName": "chenglinghong",
                "telephone": "13008142306",
                "gender": 1,
                "birthday": 1556035200000,
                "country": "China",
                "province": "sichuan",
                "city": "chegndu",
                "position": "xhu",
                "latitude": "10.10",
                "longitude": "34.00",
                "avatarUrl": "http://api.xhunccd.top/graduation/file/download?file=D:/data/app/graduation/file/user/20190414/1555251226080_833.jpg",
                "type": 0,
                "description": "test"
            },
            "newUser": false        // 用户是否为新用户
        }
    }
    
返回说明

- userInfo: 用户基本信息
- newUser: 用户是否为新用户。如果是新用户，需要进行下一步选择用户标签的操作。不是新用户则不需要
    
#### 四、更新用户基本信息

    PUT /graduation/user/user
    
参数

    nickName [string] : 用户昵称  【必填】
    realName [string] : 真实姓名
    country [string] : 国家【必填】
    province [string] : 省份【必填】
    city [string] : 城市【必填】
    position [string] : 详细地址
    latitude [string] : 经度【必填】
    longitude [string] : 纬度【必填】
    description [string] : 描述
    gender [int] : 性别（0：保密 1：男 2：女）【必填】
    birthday [date] : 出生日期【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 五、修改密码

    PUT /graduation/user/password
    
参数

    smsCode [string] : 短信验证码  【必填】
    password [string] : 新密码【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 五、修改头像

    PUT /graduation/user/avatar
    
参数

    avatarUrl [string] : 用户头像URL  【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 六、获取用户行为历史

    GET /graduation/user/history
    
参数

    pageNo [long] : 页码，默认为1
    pageSize [long] : 每页行数，默认为10
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 39,
                    "gmtCreate": 1556528629000,
                    "gmtModified": 1556528629000,
                    "userId": 3,
                    "goodsId": 1,
                    "goods": {
                        "id": 1,
                        "gmtCreate": 1556528551000,
                        "gmtModified": 1556528551000,
                        "name": "test",
                        "description": "test",
                        "catalogOneId": 1,
                        "catalogTwoId": 1,
                        "goodsInfo": "test  ",
                        "price": 1,
                        "remarks": "test",
                        "businessInfoId": 1000,
                        "coverImg": "http://pic37.nipic.com/20140113/8800276_184927469000_2.png"
                    },
                    "behavior": 9
                },
                {
                    "id": 36,
                    "gmtCreate": 1556330544000,
                    "gmtModified": 1556330544000,
                    "userId": 3,
                    "goodsId": 1,
                    "goods": {
                        "id": 1,
                        "gmtCreate": 1556528551000,
                        "gmtModified": 1556528551000,
                        "name": "test",
                        "description": "test",
                        "catalogOneId": 1,
                        "catalogTwoId": 1,
                        "goodsInfo": "test  ",
                        "price": 1,
                        "remarks": "test",
                        "businessInfoId": 1000,
                        "coverImg": "http://pic37.nipic.com/20140113/8800276_184927469000_2.png"
                    },
                    "behavior": 9
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 20
        }
    }
    
    behavior说明：1点击, 2加入购物车, 3购买, 4评价-1, 5评价-2, 6评价-3, 7评价-4， 8评价-5， 9搜索， 10图像识别