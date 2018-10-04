package com.apexmob.casus.client.stomp;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
//TODO docs
/**
 * SimpleWebSocketContainerProvider is a simple implementation of the {@link WebSocketContainerProvider} interface. It
 * delegates to the ContainerProvider.getWebSocketContainer() implementation within javax.websocket.ContainerProvider.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleWebSocketContainerProvider implements WebSocketContainerProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public WebSocketContainer getWebSocketContainer() {
        return ContainerProvider.getWebSocketContainer();
    }
}
