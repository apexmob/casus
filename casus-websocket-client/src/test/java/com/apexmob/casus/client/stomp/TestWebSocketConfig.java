package com.apexmob.casus.client.stomp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class TestWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Value("${casus.stomp.connect.path}")
    String stompPath;

    @Value("${cacus.stomp.topic.prefix:/queue}")
    String topicPrefix;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(topicPrefix);
        config.setApplicationDestinationPrefixes(topicPrefix);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(stompPath).setAllowedOrigins("*");
        registry.addEndpoint(stompPath).setAllowedOrigins("*").withSockJS();
    }

}
