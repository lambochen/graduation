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
            "gmtCreate": 1555301029000,
            "gmtModified": 1555301029000,
            "name": "test",
            "description": "test",
            "goodsId": 1,
            "userId": 3,
            "price": 1,
            "remarks": "test",
            "postCountry": "test",
            "postProvince": "test",
            "postCity": "teset",
            "postPosition": "test"
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
                    "gmtCreate": 1555301029000,
                    "gmtModified": 1555301029000,
                    "name": "test",
                    "description": "test",
                    "goodsId": 1,
                    "userId": 3,
                    "price": 1,
                    "remarks": "test",
                    "postCountry": "test",
                    "postProvince": "test",
                    "postCity": "teset",
                    "postPosition": "test"
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
                    "gmtCreate": 1555301029000,
                    "gmtModified": 1555301029000,
                    "name": "test",
                    "description": "test",
                    "goodsId": 1,
                    "userId": 3,
                    "price": 1,
                    "remarks": "test",
                    "postCountry": "test",
                    "postProvince": "test",
                    "postCity": "teset",
                    "postPosition": "test"
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }

