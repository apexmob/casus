package com.apexmob.casus.client.stomp;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.client.GameEventDistributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/**
 * StompSessionManager is a StompSessionHandlerAdapter that handles session event processing for the stomp interaction.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class StompSessionManager extends StompSessionHandlerAdapter {

    //TODO refactor to take in a logger as input
    private Logger logger = LoggerFactory.getLogger(StompSessionManager.class);
    private final GameEventDistributor distributor;
    private final StompConfigurationService configurationService;

    /**
     * Construct a new instance.
     * @param distributor The event distributor to which subscribed events should be communicated.
     * @param configurationService The service providing Stomp configuration.
     */
    public StompSessionManager(GameEventDistributor distributor, StompConfigurationService configurationService) {
        this.distributor = distributor;
        this.configurationService = configurationService;
    }

    /**
     * Handle an exception that occurred.
     * @param session The stomp session.
     * @param command The stomp command.
     * @param headers The stomp headers.
     * @param payload The message payload.
     * @param exception The exception that occurred.
     */
    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Unable to process stomp interaction", exception);
    }

    /**
     * Determine the type of payload.
     * @param headers The headers for the message.
     * @return The Type.
     */
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return configurationService.resolve(headers.getDestination());
    }

    /**
     * Handle the payload received.
     * @param headers The headers for the message.
     * @param payload The message payload.
     */
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Type type = getPayloadType(headers);
        if (type != null) {
            distributor.distribute(type, (GameEvent) payload);
        } else {
            logger.info("Unrecognized payload type, destination={}", headers.getDestination());
        }
    }

    /**
     * Handle transport exceptions.
     * @param session The stomp session.
     * @param exception The exception that occurred.
     */
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("Unable to establish stomp session", exception);
        super.handleTransportError(session, exception);
    }

}
