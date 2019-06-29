package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.api.vo.ChatListVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.ChatDao;
import com.chenlinghong.graduation.repository.domain.Chat;
import com.chenlinghong.graduation.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 客服聊天
 * @Author chenlinghong
 * @Date 2019/4/14 23:59
 * @Version V1.0
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;

    @Override
    public int insert(Chat chat) {
        if (chat == null) {
            log.error("ChatService#insert: param is null. chat={}", chat);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        chat.setChatId(generateChatId(chat.getSender(), chat.getReceiver()));
        return chatDao.insert(chat);
    }

    @Override
    public int deleteById(long id) {
        return chatDao.deleteById(id);
    }

    @Override
    public Chat getById(long id) {
        return chatDao.getById(id);
    }

    @Override
    public PageDto<Chat> listAll(long pageNo, long pageSize) {
        return new PageDto<>();
    }

    @Override
    public int update(Chat chat) {
        return 0;
    }


    @Override
    public PageDto<Chat> listByChatId(String chatId, long pageNo, long pageSize) {
        List<Chat> chatList = chatDao.listByChat(chatId, (pageNo - 1) * pageSize, pageSize);
        long total = chatDao.countByChat(chatId);
        return new PageDto<>(chatList, pageNo, pageSize, total);
    }

    @Override
    public PageDto<Chat> listBySenderReceiver(long sender, long receiver, long pageNo, long pageSize) {
        String chatId = generateChatId(sender, receiver);
        // 更新为已读消息
        chatDao.updateRead(chatId);
        return listByChatId(chatId, pageNo, pageSize);
    }

    @Override
    public PageDto<ChatListVo> listChat(long userId, long pageNo, long pageSize) {
        List<ChatListVo> chatListVoList = chatDao.listChat(userId, (pageNo - 1) * pageSize, pageSize);
        long total = chatDao.countListChat(userId);
        return new PageDto<>(chatListVoList, pageNo, pageSize, total);
    }

    /**
     * 生成会话ID
     *
     * @param sender
     * @param receiver
     * @return
     */
    public static String generateChatId(long sender, long receiver) {
        if (sender > receiver) {
            long tmp = sender;
            sender = receiver;
            receiver = tmp;
        }
        return sender + "=" + receiver;
    }
}
