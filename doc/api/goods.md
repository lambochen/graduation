

## 商品模块

#### 一、获取目录、获取一级目录（内嵌二级目录列表）

    GET /graduation/goods/catalog
    GET /graduation/goods/catalog/one
    
参数

    pageNo [int] : 当前页数
    pageSize [int] : 每页条数
    
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
                    "catalogTwoList": null
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }
    

