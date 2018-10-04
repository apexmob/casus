package com.apexmob.casus.server;

import com.apexmob.casus.BaseService;
import com.apexmob.casus.GameEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleWebSocketUserEventMessagingService extends BaseService implements WebSocketUserEventMessagingService {

    private final SimpMessagingTemplate messagingTemplate;

    public SimpleWebSocketUserEventMessagingService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendEvent(String user, String destination, GameEvent event) {
        messagingTemplate.convertAndSendToUser(user, destination, event);
    }
}
