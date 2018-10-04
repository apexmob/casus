package com.apexmob.casus.client.stomp;

import org.junit.Test;

import javax.websocket.ContainerProvider;

import static junit.framework.TestCase.assertSame;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleWebSocketContainerProviderTest {

    @Test
    public void testGetWebSocketContainer_whenCalled_thenReturnAnInstanceWithSameClassAsContainerProvider() {
        SimpleWebSocketContainerProvider provider = new SimpleWebSocketContainerProvider();
        assertSame(ContainerProvider.getWebSocketContainer().getClass(), provider.getWebSocketContainer().getClass());
    }
}
