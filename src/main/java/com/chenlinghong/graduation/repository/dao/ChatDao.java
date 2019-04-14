package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.api.vo.ChatListVo;
import com.chenlinghong.graduation.repository.domain.Chat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 客服消息DAO
 * @Author chenlinghong
 * @Date 2019/4/2 17:48
 **/
public interface ChatDao {

    /**
     * 新增聊天消息
     *
     * @param chat
     * @return
     */
    int insert(Chat chat);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 批量删除自己发送消息
     *
     * @param sender
     * @return
     */
    int deleteBySender(long sender);

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    Chat getById(long id);

    /**
     * 根据会话ID分页获取
     *
     * @param chatId
     * @param offset
     * @param rows
     * @return
     */
    List<Chat> listByChat(@Param("chatId") String chatId,
                          @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据会话ID获取记录数
     *
     * @param chatId
     * @return
     */
    int countByChat(String chatId);


    /**
     * 分页获取聊天列表
     *
     * @param userId
     * @param offset
     * @param rows
     * @return
     */
    List<ChatListVo> listChat(@Param("userId") long userId,
                              @Param("offset") long offset, @Param("rows") long rows);
    int countListChat(long userId);

    /**
     * 更新消息是否已读
     * @param chatId
     * @return
     */
    int updateRead(@Param("chatId") String chatId);
}
