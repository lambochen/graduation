
## 商户信息

#### 一、根据ID获取

    GET /graduation/business/business/{id}
    
参数

    id [long] : 商户ID，在请求路径填写 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "id": 1,
            "gmtCreate": 1553938458000,
            "gmtModified": 1553938458000,
            "name": "test",
            "description": null,
            "openingTime": 1553938458000,
            "country": null,
            "province": null,
            "city": null,
            "position": null,
            "longitude": null,
            "latitude": null,
            "avatarUrl": "test",
            "telephone": "13008142306",
            "userId": 1000
        }
    }
    