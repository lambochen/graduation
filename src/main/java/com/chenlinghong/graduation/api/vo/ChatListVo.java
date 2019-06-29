package com.chenlinghong.graduation.api.vo;

import com.chenlinghong.graduation.repository.domain.BaseDomain;
import com.chenlinghong.graduation.repository.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 聊天列表Vo
 * @Author chenlinghong
 * @Date 2019/3/28 21:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatListVo extends BaseDomain {

    /**
     * 发送方
     */
    private Long sender;

    private User senderUser;

    /**
     * 接收方
     */
    private Long receiver;

    private User receiverUser;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 会话ID
     */
    private String chatId;

    /**
     * 是否已读
     */
    private Integer read;

    /**
     * 未读数目
     */
    private Integer readCount;

}
