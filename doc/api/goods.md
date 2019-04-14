

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
                    "id": 2,
                    "gmtCreate": 1555225976000,
                    "gmtModified": 1555225976000,
                    "name": "test-1",       //目录名称
                    "description": null,
                    "catalogTwoList": null
                },
                {
                    "id": 1,
                    "gmtCreate": 1553425721000,
                    "gmtModified": 1553425721000,
                    "name": "test",
                    "description": null,
                    "catalogTwoList": null
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 2
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
                    "gmtCreate": 1555225869000,
                    "gmtModified": 1555225869000,
                    "name": "test-1",       //二级目录名称
                    "description": null,
                    "catalogOneId": 1
                },
                {
                    "id": 1,
                    "gmtCreate": 1553426662000,
                    "gmtModified": 1553426662000,
                    "name": "test",
                    "description": null,
                    "catalogOneId": 1
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
            "gmtCreate": 1553935461000,
            "gmtModified": 1553935461000,
            "name": "test",
            "description": "test",
            "catalogOneId": 1,
            "catalogTwoId": 1,
            "goodsInfo": "test  ",
            "price": 1,
            "remarks": "test",
            "businessInfoId": 1000
        }
    }