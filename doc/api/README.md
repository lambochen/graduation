
# API接口说明

1、所有接口返回为Result对象。

    {
        "code": 0,
        "msg": "请求成功",
        "data": {}
    }
    
data对象为分页对象时：
    
    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": {},         // 具体数据
            "pageNo": 1,        // 当前页
            "pageSize": 10,     // 每页条数
            "totalCount": 100   // 总条数
        }
    }
    
- code: 0表示请求成功，非0表示请求失败
- msg: 返回结果提示文字
- data: code为0时表示返回结果数据，非0时为null

2、时间格式约定，所有时间格式为时间戳形式返回

3、具体数据字段含义，参考：[数据库设计说明](https://github.com/chenlinghong/graduation/blob/develop/doc/database/database-description.md)

4、测试账号

    telephone: 13008142306
    password : 123456