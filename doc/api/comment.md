
## 评论模块

#### 一、新增评论

    POST /graduation/comment/comment
    
参数

    goodsId [long] : 商品ID 【必填】
    content [string] : 评论内容 【必填】
    imgOne [string] : 图片1 
    imgTwo [string] : 图片2 
    imgThree [string] : 图片3
    score [int] : 评价分数  【必填，要求是1-5】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 二、根据ID删除

    DELETE /graduation/comment/comment/{id}
    
参数

    id [long] : 评论ID 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 三、根据用户获取

    GET /graduation/comment/comment/user/list
    
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
                    "gmtCreate": 1556116090000,
                    "gmtModified": 1556116090000,
                    "goodsId": 1,
                    "userId": 3,
                    "content": "test",
                    "imgOne": "",
                    "imgTwo": "",
                    "imgThree": "",
                    "score": 3
                },
                {
                    "id": 1,
                    "gmtCreate": 1556033203000,
                    "gmtModified": 1556033203000,
                    "goodsId": 1,
                    "userId": 3,
                    "content": "test",
                    "imgOne": null,
                    "imgTwo": null,
                    "imgThree": null,
                    "score": 1
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 2
        }
    }
    
    
#### 四、根据商品获取

    GET /graduation/comment/comment/goods/{goodsId}
    
参数

    goodsId [long] : 商品ID 【必填】
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
                    "gmtCreate": 1556116090000,
                    "gmtModified": 1556116090000,
                    "goodsId": 1,
                    "userId": 3,
                    "content": "test",
                    "imgOne": "",
                    "imgTwo": "",
                    "imgThree": "",
                    "score": 3
                },
                {
                    "id": 1,
                    "gmtCreate": 1556033203000,
                    "gmtModified": 1556033203000,
                    "goodsId": 1,
                    "userId": 3,
                    "content": "test",
                    "imgOne": null,
                    "imgTwo": null,
                    "imgThree": null,
                    "score": 1
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 2
        }
    }

    
