package com.apexmob.casus.client.stomp;

import javax.websocket.WebSocketContainer;

/**
 * WebSocketContainerProvider represents the basic interface for obtaining a WebSocketContainer.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface WebSocketContainerProvider {

    /**
     * Obtain a WebSocketContainer.
     *
     * @return The WebSocketContainer.
     */
    WebSocketContainer getWebSocketContainer();
}
