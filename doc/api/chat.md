
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
    content [long] : 消息内容 【必填】
    
返回

    {
        "code": 0,
        "msg": "请求成功",
        "data": null
    }
    