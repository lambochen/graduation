## 商品订单模块

#### 一、新增订单

    POST /graduation/order/order
    
参数

    name [string] : 订单名称
    description [string] : 描述
    goodsId [long]: 商品ID    【必填】
    price [double]: 价钱  【必填】
    remarks [string]: 备注
    postCountry [string]: 国家
    postProvince [string]: 省份
    postCity [string]: 城市
    postPosition [string]: 具体地址
    number [int] : 购买数量 【必填,正数】
    smsCode [string] : 短信验证码 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 二、根据ID获取订单详情

    GET /graduation/order/order/{id}
    
参数

    id [long]: 订单ID    【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "id": 2,
            "gmtCreate": 1556203011000,
            "gmtModified": 1556203011000,
            "name": "test",
            "description": "test",
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
            "userId": 3,
            "price": 1,
            "remarks": "test",
            "postCountry": "test",
            "postProvince": "test",
            "postCity": "teset",
            "postPosition": "test",
            "number": 1
        }
    }
  
#### 三、根据用户获取订单列表

    GET /graduation/order/order/user/list
    
参数

    pageNo [long] : 当前页数，默认为1
    pageSize [long] : 每页条数，默认为10
        
返回  

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 2,
                    "gmtCreate": 1556203011000,
                    "gmtModified": 1556203011000,
                    "name": "test",
                    "description": "test",
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
                    "userId": 3,
                    "price": 1,
                    "remarks": "test",
                    "postCountry": "test",
                    "postProvince": "test",
                    "postCity": "teset",
                    "postPosition": "test",
                    "number": 1
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }
    
#### 四、根据商品获取订单列表

    GET /graduation/order/order/goods/{id}
    
参数

    id [long]: 商品ID    【必填】
    pageNo [long] : 当前页数，默认为1
    pageSize [long] : 每页条数，默认为10
        
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 2,
                    "gmtCreate": 1556203011000,
                    "gmtModified": 1556203011000,
                    "name": "test",
                    "description": "test",
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
                    "userId": 3,
                    "price": 1,
                    "remarks": "test",
                    "postCountry": "test",
                    "postProvince": "test",
                    "postCity": "teset",
                    "postPosition": "test",
                    "number": 1
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }
