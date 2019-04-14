package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.api.vo.ChatListVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.Chat;

/**
 * @Description 客服消息
 * @Author chenlinghong
 * @Date 2019/4/14 23:58
 * @Version V1.0
 */
public interface ChatService extends IBaseService<Chat> {

    /**
     * 根据会话ID获取聊天记录
     *
     * @param chatId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<Chat> listByChatId(String chatId, long pageNo, long pageSize);

    /**
     * 根据用户ID获取聊天记录
     *
     * @param sender
     * @param receiver
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<Chat> listBySenderReceiver(long sender, long receiver, long pageNo, long pageSize);

    /**
     * 获取聊天
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<ChatListVo> listChat(long userId, long pageNo, long pageSize);
}
