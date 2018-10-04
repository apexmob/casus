package com.apexmob.casus.sample.websocket.server;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.sample.SampleEvent;
import com.apexmob.casus.sample.SampleEventDispatcher;
import com.apexmob.casus.server.WebSocketUserEventMessagingService;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SampleWebSocketModule extends BaseModule {



    @Override
    public void start(GameEngine game) {
        super.start(game);

        WebSocketUserEventMessagingService userEventMessagingService = (WebSocketUserEventMessagingService) game.getService(WebSocketUserEventMessagingService.class.getName());

        SampleEventDispatcher sampleEventDispatcher = (SampleEventDispatcher) game.getGameEventDispatcher(SampleEvent.class);
        if (sampleEventDispatcher != null) {
            sampleEventDispatcher.addListener(new ClientCommunicatingSampleEventListener(userEventMessagingService));
        }
    }

    @Override
    public void stop(GameEngine game) {
        super.stop(game);
    }
}
