
## 购物车模块

#### 一、添加购物车

    POST /graduation/shopping/cart/goods/{goodsId}
    
参数

    goodsId [long] : 商品ID 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 二、获得购物车列表

    GET /graduation/shopping/cart/goods/list
    
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
                    "gmtCreate": 1555266021000,
                    "gmtModified": 1555266021000,
                    "goodsId": 2,
                    "goods": {
                        "id": 2,
                        "gmtCreate": 1555228757000,
                        "gmtModified": 1555228757000,
                        "name": "test",
                        "description": "test",
                        "catalogOneId": 1,
                        "catalogTwoId": 1,
                        "goodsInfo": "test  ",
                        "price": 1,
                        "remarks": "test",
                        "businessInfoId": 1000
                    },
                    "userId": 3,
                    "count": 3
                },
                {
                    "id": 1,
                    "gmtCreate": 1555266016000,
                    "gmtModified": 1555266016000,
                    "goodsId": 1,
                    "goods": {
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
                    },
                    "userId": 3,
                    "count": 3
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 2
        }
    }
   
#### 三、批量删除购物车

    DELETE /graduation/shopping/cart/goods/list
    
参数

    idList [string] : id组成的list,各ID间用“,”分割,例“1，2，3”
    
返回 

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
