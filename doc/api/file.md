## 文件上传

#### 一、上传文件

    POST /graduation/file/upload
    
参数

    files: 文件名称,支持多个文件上传 【必填】
    modular [Int]： 模块，超出模块范围上传不成功
    模块说明：
        1,"商品模块"； 
        2,"用户模块"
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": [
            "http://api.xhunccd.top/graduation/file/download?file=D:/data/app/graduation/file/goods/20190414/1555249227898_784.jpg"
        ]
    }
 
 
#### 二、下载文件

    GET /graduation/file/download
    
参数

    file: 文件名称 【必填】
    
返回
 
    下载成功直接展示图片
    下载不成功时，提示错误信息
    
    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }