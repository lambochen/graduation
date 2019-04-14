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
    
    

