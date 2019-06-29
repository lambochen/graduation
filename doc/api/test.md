## 测试

#### 一、获取所有测试数据

    GET /graduation/test/all
    
参数

    pageNo [int] : 第几页，默认为1
    pageSize [int] : 每页条数，默认为10
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 1000,
                    "gmtCreate": 1552481633000,
                    "gmtModified": 1552481633000,
                    "name": "test"
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 1
        }
    }
