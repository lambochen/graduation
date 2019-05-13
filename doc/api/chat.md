
## 聊天相关

参考：

[1、前端示例代码 - SockJS](https://paste.ubuntu.com/p/wY9vQFdF4b/)
[2、前端连接示例代码 - WebSocket](https://paste.ubuntu.com/p/kSFW4kjtJp/)


建立socket连接地址：
    
    ws://api.xhunccd.top/graduation/websocket       原生socket
    wss://api.xhunccd.top/graduation/websocket       原生socket 安全连接方式
    
    http://api.xhunccd.top/graduation/websocket/sockjs      sockJS
    https://api.xhunccd.top/graduation/websocket/sockjs      sockJS 安全连接方式


订阅消息路径：

    订阅自己的消息：/user/{userId}/message   说明：订阅别人发给自己的单独聊天消息


#### 一、发送消息给指定用户

    POST /graduation/chat/user
    
参数

    receiver [long] : 接收方用户ID 【必填】
    content [string] : 消息内容 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
   
#### 二、获取聊天记录

    GET /graduation/chat/chat/{receiver}
    
参数

    receiver [long] : 接收方用户ID, 请求路径填写 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 7,
                    "gmtCreate": 1555261574000,
                    "gmtModified": 1555261574000,
                    "sender": 3,
                    "receiver": 1,
                    "content": "test",
                    "chatId": "1=3",
                    "read": null
                },
                {
                    "id": 5,
                    "gmtCreate": 1555259197000,
                    "gmtModified": 1555259197000,
                    "sender": 3,
                    "receiver": 1,
                    "content": "test",
                    "chatId": "1=3",
                    "read": null
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 2
        }
    }

    
    
#### 三、获取聊天列表

    GET /graduation/chat/chat/list
    
参数

    无
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": {
            "data": [
                {
                    "id": 7,
                    "gmtCreate": 1555261574000,
                    "gmtModified": null,
                    "sender": 3,
                    "senderUser": {
                        "id": 3,
                        "gmtCreate": 1554282432000,
                        "gmtModified": 1555251335000,
                        "nickName": "chenglinghong",
                        "realName": "chenglinghong",
                        "telephone": "13008142306",
                        "gender": 1,
                        "birthday": 1555171200000,
                        "country": "China",
                        "province": "sichuan",
                        "city": "chegndu",
                        "position": "xhu",
                        "latitude": "10.10",
                        "longitude": "10.0",
                        "avatarUrl": "http://api.xhunccd.top/graduation/file/download?file=D:/data/app/graduation/file/user/20190414/1555251226080_833.jpg",
                        "type": 0,
                        "description": "test"
                    },
                    "receiver": 1,
                    "receiverUser": null,
                    "content": "test",
                    "chatId": "1=3",
                    "read": 1,
                    "readCount": 2
                },
                {
                    "id": 6,
                    "gmtCreate": 1555260556000,
                    "gmtModified": null,
                    "sender": 3,
                    "senderUser": {
                        "id": 3,
                        "gmtCreate": 1554282432000,
                        "gmtModified": 1555251335000,
                        "nickName": "chenglinghong",
                        "realName": "chenglinghong",
                        "telephone": "13008142306",
                        "gender": 1,
                        "birthday": 1555171200000,
                        "country": "China",
                        "province": "sichuan",
                        "city": "chegndu",
                        "position": "xhu",
                        "latitude": "10.10",
                        "longitude": "10.0",
                        "avatarUrl": "http://api.xhunccd.top/graduation/file/download?file=D:/data/app/graduation/file/user/20190414/1555251226080_833.jpg",
                        "type": 0,
                        "description": "test"
                    },
                    "receiver": 2,
                    "receiverUser": null,
                    "content": "测试消息",
                    "chatId": "2:3",
                    "read": 1,
                    "readCount": 1
                },
                {
                    "id": 1,
                    "gmtCreate": 1555258840000,
                    "gmtModified": null,
                    "sender": 3,
                    "senderUser": {
                        "id": 3,
                        "gmtCreate": 1554282432000,
                        "gmtModified": 1555251335000,
                        "nickName": "chenglinghong",
                        "realName": "chenglinghong",
                        "telephone": "13008142306",
                        "gender": 1,
                        "birthday": 1555171200000,
                        "country": "China",
                        "province": "sichuan",
                        "city": "chegndu",
                        "position": "xhu",
                        "latitude": "10.10",
                        "longitude": "10.0",
                        "avatarUrl": "http://api.xhunccd.top/graduation/file/download?file=D:/data/app/graduation/file/user/20190414/1555251226080_833.jpg",
                        "type": 0,
                        "description": "test"
                    },
                    "receiver": 2,
                    "receiverUser": null,
                    "content": "test",
                    "chatId": "2=3",
                    "read": 1,
                    "readCount": 1
                }
            ],
            "pageNo": 1,
            "pageSize": 10,
            "totalCount": 3
        }
    }
    




