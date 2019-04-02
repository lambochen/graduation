package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 消息客服
 * @Author chenlinghong
 * @Date 2019/4/2 17:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat extends BaseDomain {

    private static final long serialVersionUID = 3736428054420072228L;

    /**
     * 发送者ID
     */
    private Long sender;

    /**
     * 接收者ID
     */
    private Long receiver;

    /**
     * 消息主体
     */
    private String content;

    /**
     * 会话ID
     */
    private String chatId;
}
