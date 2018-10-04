package com.apexmob.casus.server;

import com.apexmob.casus.BaseService;
import com.apexmob.casus.GameEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleWebSocketEventMessagingService extends BaseService implements WebSocketEventMessagingService {

    private final SimpMessagingTemplate messagingTemplate;

    public SimpleWebSocketEventMessagingService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendEvent(String destination, GameEvent event) {
        messagingTemplate.convertAndSend(destination, event);
    }
}
