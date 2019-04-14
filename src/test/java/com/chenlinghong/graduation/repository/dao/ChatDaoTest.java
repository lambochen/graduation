package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.api.vo.ChatListVo;
import com.chenlinghong.graduation.repository.domain.Chat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatDaoTest {

    @Autowired
    private ChatDao chatDao;

    @Test
    public void insert() {
        Chat chat = new Chat(2L, 3L, "test", "2=3", 1);
        int result = chatDao.insert(chat);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listByChat() {
        List<Chat> chatList = chatDao.listByChat("2=3", 0L, 10L);
        System.out.println(chatList);
    }

    @Test
    public void listChat() {
        List<ChatListVo> chatListVoList = chatDao.listChat(1, 0, 10);
        System.out.println(chatListVoList);
    }

}