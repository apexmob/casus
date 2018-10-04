package com.apexmob.casus.server;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class WebSocketEventMessagingConfigurer implements GameEngineConfigurer {

    private final WebSocketEventMessagingService eventMessagingService;
    private final WebSocketUserEventMessagingService userEventMessagingService;

    public WebSocketEventMessagingConfigurer(WebSocketEventMessagingService eventMessagingService, WebSocketUserEventMessagingService userEventMessagingService) {
        this.eventMessagingService = eventMessagingService;
        this.userEventMessagingService = userEventMessagingService;
    }

    @Override
    public void configure(GameEngine gameEngine) {
        if (gameEngine.getService(WebSocketEventMessagingService.class.getName()) == null) {
            gameEngine.putService(WebSocketEventMessagingService.class.getName(), eventMessagingService);
        }

        if (gameEngine.getService(WebSocketUserEventMessagingService.class.getName()) == null) {
            gameEngine.putService(WebSocketUserEventMessagingService.class.getName(), userEventMessagingService);
        }
    }
}
