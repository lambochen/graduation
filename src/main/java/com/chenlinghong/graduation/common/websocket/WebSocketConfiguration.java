package com.chenlinghong.graduation.common.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @Description websocket 配置类
 * @Author chenlinghong
 * @Date 2019/4/7 11:33
 * @Version V1.0
 */
@Configuration
// 注解开启使用STOMP协议来传输基于代理（message broker）的消息，
// 这时控制器支持使用@MessageMapping，就和使用@RequestMapping一样
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    // 配置消息代理（Message Broker）
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 点对点应配置一个/user的消息代理，广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic", "/user");

        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user
        registry.setUserDestinationPrefix("/user");

        super.configureMessageBroker(registry);
    }

    @Override
    // 注册STOMP协议的节点（endpoint），并映射指定的URL
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/websocket").setAllowedOrigins("*");
        registry.addEndpoint("/websocket/sockjs").setAllowedOrigins("*").withSockJS();
    }

}
