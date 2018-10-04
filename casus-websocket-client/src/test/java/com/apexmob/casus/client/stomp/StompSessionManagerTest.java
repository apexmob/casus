package com.apexmob.casus.client.stomp;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.client.GameEventDistributor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class StompSessionManagerTest {

    private StompSessionManager manager;
    private GameEventDistributor distributor;
    private StompConfigurationService configurationService;
    private StompHeaders headers;

    @Before
    public void before() {
        distributor = Mockito.mock(GameEventDistributor.class);
        configurationService = Mockito.mock(StompConfigurationService.class);
        Mockito.when(configurationService.resolve("test")).thenReturn(String.class);

        manager = new StompSessionManager(distributor, configurationService);

        headers = Mockito.mock(StompHeaders.class);
    }

    @Test
    public void testHandleException_whenExceptionHandled_thenNoExceptionThrown() {
        StompSession session = Mockito.mock(StompSession.class);
        StompCommand command = StompCommand.STOMP;
        StompHeaders headers = Mockito.mock(StompHeaders.class);
        byte[] payload = new byte[0];
        Exception exception = new Exception("Test Exception");

        manager.handleException(session, command, headers, payload, exception);
    }

    @Test
    public void testHandleTransportError_whenTransportExceptionHandled_thenNoExceptionThrown() {
        StompSession session = Mockito.mock(StompSession.class);
        Exception exception = new Exception("Test Exception");

        manager.handleTransportError(session, exception);
    }

    @Test
    public void testGetPayloadType_whenTypeResolvable_thenReturnType() {
        Mockito.when(headers.getDestination()).thenReturn("test");

        assertSame(String.class, manager.getPayloadType(headers));
    }

    @Test
    public void testGetPayloadType_whenTypeNotResolvable_thenReturnNull() {
        Mockito.when(headers.getDestination()).thenReturn("fail");

        assertNull(manager.getPayloadType(headers));
    }

    @Test
    public void testHandleFrame_whenTypeResolvable_thenDistributeEvent() {
        Mockito.when(headers.getDestination()).thenReturn("test");

        manager.handleFrame(headers, new MockEvent());

        Mockito.verify(distributor, Mockito.times(1)).distribute(eq(String.class), any(MockEvent.class));
    }

    @Test
    public void testHandleFrame_whenTypeNotResolvable_thenDoNotDistributeEvent() {
        Mockito.when(headers.getDestination()).thenReturn("fail");

        manager.handleFrame(headers, new MockEvent());

        Mockito.verifyZeroInteractions(distributor);
    }

    private class MockEvent implements GameEvent {

    }
}
