## 显微镜

#### 一、商品浏览记录上报

    POST /graduation/microscope/click
    
参数

    goodsId [long] : 商品ID 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    
#### 二、上报用户行为

    POST /graduation/microscope/report
    
参数

    goodsId [long] : 商品ID 【必填】
    behavior [int] : 用户行为代码
    
参数说明

    CLICK(1, "点击"),
    ADD_TO_CART(2, "加入购物车"),
    PURCHASE(3, "购买"),
    COMMENT_SCORE_1(4, "评价-1分"),
    COMMENT_SCORE_2(5, "评价-2分"),
    COMMENT_SCORE_3(6, "评价-3分"),
    COMMENT_SCORE_4(7, "评价-4分"),
    COMMENT_SCORE_5(8, "评价-5分"),
    SEARCH(9, "搜索"),
    PATTERN_RECOGNITION(10, "模式识别")
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }