package com.chenlinghong.graduation.api.controller;

import com.alibaba.fastjson.JSON;
import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.domain.Chat;
import com.chenlinghong.graduation.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private ChatService chatService;

    /**
     * 发送消息给指定用户
     *
     * @param receiver 接收者
     * @param content  消息内容
     * @param request
     * @return
     */
    @PostMapping("/user")
    public ResultVo sendMessageForUser(Long receiver, String content, HttpServletRequest request) {
        log.info("chatController#sendToUser: receiver={}, content={}, request={}", receiver, content, request);
        long sender = sessionUtil.getUserId(request);
        Chat chat = new Chat(sender, receiver, content);
        /**
         * 写入数据库
         */
        int dbResult = chatService.insert(chat);
        if (dbResult != 1) {
            log.error("ChatController#sendMessageForUser: failed insert chat. receiver={}, content={}, request={}"
                    , receiver, content, request);
            throw new BusinessException(ErrorEnum.CHAT_INSERT_ERROR);
        }
        String payload = JSON.toJSONString(chat);
        template.convertAndSendToUser("" + receiver, "/message", payload);

        return ResultUtil.success();
    }


    /**
     * 根据用户ID获取聊天记录
     *
     * @param receiver 对方ID
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/chat/{receiver}")
    public ResultVo listBySenderReceiver(
            @PathVariable(value = "receiver") Long receiver,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
            HttpServletRequest request) {
        long sender = sessionUtil.getUserId(request);
        return ResultUtil.success(chatService.listBySenderReceiver(sender, receiver, pageNo, pageSize));
    }

    /**
     * 获取聊天列表
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/chat/list")
    public ResultVo listChat(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                             HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        return ResultUtil.success(chatService.listChat(userId, pageNo, pageSize));
    }


}
