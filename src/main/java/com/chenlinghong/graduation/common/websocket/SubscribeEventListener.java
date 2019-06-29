package com.chenlinghong.graduation.common.websocket;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * @Description 订阅监听
 * @Author chenlinghong
 * @Date 2019/4/7 11:35
 * @Version V1.0
 */
@Component
public class SubscribeEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof SessionSubscribeEvent) {
            SessionSubscribeEvent sessionSubscribeEvent = (SessionSubscribeEvent) applicationEvent;
            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        }
    }
}
