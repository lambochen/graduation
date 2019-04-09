## 商品相关

一.根据一级目录查找二级二级目录列表
    GET /graduation/goodsCatalogTwo/listByGoodsCatalogOneId?
参数
    goodsCatalogOneId [Long] :（必填）一级目录id
返回
    {
            "code": 0,
            "msg": "请求成功",
            "data": [{
             "id":3,
             "gmtCreate":1554362394000,
             "gmtModified":1554362394000,
             "name":"1","description":"1",
             "catalogOneId":1,
             "catalogTwoList":null
             },   
               ... ]
            
   }
例子：/graduation/goodsCatalogTwo/listByGoodsCatalogOneId?goodsCatalogOneId=1

二.根据商品一级目录ID获取商品列表
    GET /graduation/goods/listByGoodsCatalogOneId
参数
    goodsCatalogOneId [Long] :（必填）一级目录id
返回
    {
            "code":0,
            "msg":"请求成功",
            "data":
            [{"id":5,
            "gmtCreate":1554261623000,
            "gmtModified":1554261623000,
            "name":"蛋糕",
            "description":"很好吃",
            "catalogOneId":2,
            "catalogTwoId":2,
            "goodsInfo":"手作蛋糕",
            "price":2.0,
            "remarks":"好吃",
            "businessInfoId":null}
                ...
            ]
    }
例子：/graduation/goods/listByGoodsCatalogOneId?goodsCatalogOneId=2

三.根据商品二级目录ID获取商品列表
    GET /graduation/goods/listByGoodsCatalogTwoId
参数
    goodsCatalogTwoId [Long] :（必填）二级目录id
返回
    {
            "code":0,
            "msg":"请求成功",
            "data":
            [{"id":5,
            "gmtCreate":1554261623000,
            "gmtModified":1554261623000,
            "name":"蛋糕",
            "description":"很好吃",
            "catalogOneId":2,
            "catalogTwoId":2,
            "goodsInfo":"手作蛋糕",
            "price":2.0,
            "remarks":"好吃",
            "businessInfoId":null}
                ...
            ]
    }
例子：/graduation/goods/listByGoodsCatalogTwoId?goodsCatalogTwoId=2