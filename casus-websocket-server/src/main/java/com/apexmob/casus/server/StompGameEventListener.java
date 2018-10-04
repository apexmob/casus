package com.apexmob.casus.server;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.GameEventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class StompGameEventListener implements GameEventListener<GameEvent> {

    private final SimpMessagingTemplate template;
    private final String destination;

    public StompGameEventListener(String destination, SimpMessagingTemplate template) {
        this.destination = destination;
        this.template = template;
    }

    @Override
    public boolean onEvent(GameEvent gameEvent) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        //TODO destination="/queue/sample-events"
        template.convertAndSendToUser(principal.getName(), destination, gameEvent);
        return true;
    }
}
