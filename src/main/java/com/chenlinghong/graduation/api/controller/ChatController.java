package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 客服聊天消息
 * @Author chenlinghong
 * @Date 2019/4/7 11:43
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatController {

    /**
     * Spring Boot中操作WebSocket的类
     */
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 发送消息给指定用户
     * TODO 此代码仅为测试示例代码，后期完善业务逻辑处理
     * @param receiver
     * @param content
     * @param request
     * @return
     */
    @PostMapping("/message")
    public ResultVo sendMessageForUser(long receiver, String content, HttpServletRequest request){
        log.info("chatController.sendToUser: receiver={}, content={}, request={}", receiver, content, request);
        Integer sender = 1000;
        String payload = sender + " to " + receiver + ": " + content;
        /**
         * TODO 写入数据库
         */
        log.info("SocketController.sendToUser: send success, receiver={}, content={}, request={}, payload={}",
                receiver, content, request, payload);
        template.convertAndSendToUser("" + receiver, "/message", payload);

        return ResultUtil.success();
    }

}
