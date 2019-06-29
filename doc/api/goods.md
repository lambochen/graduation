

## 商品模块

#### 一、获取目录、获取一级目录（内嵌二级目录列表）

    GET /graduation/goods/catalog
    GET /graduation/goods/catalog/one
    
参数

    pageNo [int] : 当前页数，默认为1
    pageSize [int] : 每页条数，默认为10
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 1,
                    "gmtCreate": 1553425721000,
                    "gmtModified": 1553425721000,
                    "name": "test",
                    "description": null,
                    "catalogTwoList": [
                        {
                            "id": 2,
                            "gmtCreate": 1556681686000,
                            "gmtModified": 1556681686000,
                            "name": "test",
                            "description": null,
                            "catalogOneId": 1,
                            "season": 0
                        },
                        {
                            "id": 1,
                            "gmtCreate": 1556681654000,
                            "gmtModified": 1556681654000,
                            "name": "test-1",
                            "description": null,
                            "catalogOneId": 1,
                            "season": 0
                        }
                    ]
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }
    
#### 二、根据一级目录ID获取二级目录

    GET /graduation/goods/catalog/two
    
参数

    catalogOneId [int] : 一级目录ID 【必填】
    pageNo [int] : 当前页数，默认为1
    pageSize [int] : 每页条数，默认为10
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 2,
                    "gmtCreate": 1556681686000,
                    "gmtModified": 1556681686000,
                    "name": "test",
                    "description": null,
                    "catalogOneId": 1,
                    "season": 0
                },
                {
                    "id": 1,
                    "gmtCreate": 1556681654000,
                    "gmtModified": 1556681654000,
                    "name": "test-1",
                    "description": null,
                    "catalogOneId": 1,
                    "season": 0
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 2
        }
    }
    
#### 三、根据ID获取商品信息

    GET /graduation/goods/goods/{id}
    
参数

    id [long] : 商品ID 【必填，请求路径中填写】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
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
        }
    }
    
#### 四、根据一级目录ID获取商品信息列表

    GET /graduation/goods/goods/catalog/one/{catalogOneId}
    
请求示例：

    http://127.0.0.1:8080/graduation/goods/goods/catalog/one/1?pageNo=1&pageSize=10
    
参数

    catalogOneId [int] : 一级目录ID 【必填，请求路径中填写】
    pageNo [long] : 当前页数，默认为1
    pageSize [long] : 每页条数，默认为10
    
返回    

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
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
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }

#### 五、根据二级目录ID获取商品信息列表

    GET /graduation/goods/goods/catalog/two/{catalogTwoId}
    
请求示例：

    http://127.0.0.1:8080/graduation/goods/goods/catalog/two/1?pageNo=1&pageSize=10
    
参数

    catalogTwoId [int] : 一级目录ID 【必填，请求路径中填写】
    pageNo [long] : 当前页数，默认为1
    pageSize [long] : 每页条数，默认为10
    
返回 

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
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
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }


#### 六、根据商品名称模糊搜索

    GET /graduation/goods/goods/name
    
参数

    goodsName [string] : 商品名称 【必填】
    pageNo [long] : 当前页数，默认为1
    pageSize [long] : 每页条数，默认为10
    
返回 

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
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
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }













