package com.apexmob.casus.server;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.SimpleGameEngine;
import com.apexmob.casus.server.websocket.CommandController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Kirk
 * @since 1.0
 */
@Configuration
public class ServerConfig {

    @Bean
    @ConditionalOnMissingBean
    public GameEngine gameEngine() {
        GameEngine retVal = new SimpleGameEngine();

        return retVal;
    }

    @Bean
    public GameEngineConfiguration gameEngineConfiguration(GameEngine gameEngine, List<GameEngineConfigurer> configurers) {
        List<GameEngineConfigurer> configs = configurers;
        if (configs == null) {
            configs = new ArrayList<>();
        }

        for (GameEngineConfigurer configurer : configs) {
            configurer.configure(gameEngine);
        }

        gameEngine.start();

        return new GameEngineConfiguration(gameEngine, configurers);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketEventMessagingService webSocketEventMessagingService(SimpMessagingTemplate messagingTemplate) {
        return new SimpleWebSocketEventMessagingService(messagingTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketUserEventMessagingService webSocketUserEventMessagingService(SimpMessagingTemplate messagingTemplate) {
        return new SimpleWebSocketUserEventMessagingService(messagingTemplate);
    }

    @Bean
    public WebSocketEventMessagingConfigurer webSocketEventMessagingConfigurer(WebSocketEventMessagingService eventMessagingService, WebSocketUserEventMessagingService userEventMessagingService) {
        return new WebSocketEventMessagingConfigurer(eventMessagingService, userEventMessagingService);
    }

    @Bean
//    @ConditionalOnMissingBean
    public CommandController commandController(GameEngine gameEngine) {
        return new CommandController(gameEngine);
    }
}
