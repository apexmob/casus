package com.apexmob.casus.sample.websocket.client;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.client.GameEventDistributor;
import com.apexmob.casus.client.stomp.StompConfigurationService;
import com.apexmob.casus.net.StaticUriResolver;
import com.apexmob.casus.sample.SampleEvent;
import com.apexmob.casus.sample.SampleEventDispatcher;
import com.apexmob.casus.sample.SimpleSampleEventListener;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SampleStompModule extends BaseModule {

    private static final String SAMPLE_EVENT_DESTINATION = "/user/queue/sample-events";

    private SimpleSampleEventListener listener;
    private SampleEventDispatcher sampleEventDispatcher;

    @Override
    public void start(GameEngine game) {
        super.start(game);

        sampleEventDispatcher = new SampleEventDispatcher();
        game.putGameEventDispatcher(SampleEvent.class, sampleEventDispatcher);

        listener = new SimpleSampleEventListener();
        sampleEventDispatcher.addListener(listener);

        GameEventDistributor distributor = (GameEventDistributor) game.getService(GameEventDistributor.class.getName());
        if (distributor != null) {
            distributor.putGameEventDispatcher(SampleEvent.class, sampleEventDispatcher);
        }

        StompConfigurationService configurationService = (StompConfigurationService) game.getService(StompConfigurationService.class.getName());
        if (configurationService != null) {
            configurationService.putMapping(SAMPLE_EVENT_DESTINATION, SampleEvent.class);
            configurationService.setUriResolver(new StaticUriResolver("ws://localhost:8080/casus-sample"));
            configurationService.addSubscription("/user/queue/sample-events");
        }

    }

    @Override
    public void stop(GameEngine game) {

        StompConfigurationService configurationService = (StompConfigurationService) game.getService(StompConfigurationService.class.getName());
        if (configurationService != null) {
            configurationService.removeMapping(SAMPLE_EVENT_DESTINATION);
            configurationService.removeSubscription("/user/queue/sample-events");
        }

        SampleEventDispatcher eventDispatcher = (SampleEventDispatcher)game.getGameEventDispatcher(SampleEvent.class);
        if (eventDispatcher != null && listener != null) {
            eventDispatcher.removeListener(listener);
            game.removeGameEventDispatcher(SampleEvent.class);
        }

        super.stop(game);
    }
}
