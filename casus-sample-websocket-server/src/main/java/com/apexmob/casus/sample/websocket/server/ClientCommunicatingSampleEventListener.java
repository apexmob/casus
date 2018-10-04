package com.apexmob.casus.sample.websocket.server;

import com.apexmob.casus.sample.SampleEvent;
import com.apexmob.casus.sample.SampleEventListener;
import com.apexmob.casus.server.WebSocketUserEventMessagingService;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class ClientCommunicatingSampleEventListener implements SampleEventListener {

    private final WebSocketUserEventMessagingService userEventMessagingService;

    public ClientCommunicatingSampleEventListener(WebSocketUserEventMessagingService userEventMessagingService) {
        this.userEventMessagingService = userEventMessagingService;
    }

    @Override
    public boolean onEvent(SampleEvent event) {
        userEventMessagingService.sendEvent(event.getName(), "/queue/sample-events", event);
        return true;
    }


}
