package com.apexmob.casus.client.stomp;

import com.apexmob.casus.command.CommandEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.stomp.StompSession;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class StompCommandEventListenerTest {

    private StompCommandEventListener listener;
    private StompSession session;
    private String destination;

    @Before
    public void before() {
        session = Mockito.mock(StompSession.class);
        destination = "test";
        listener = new StompCommandEventListener(session, destination);
    }

    @Test
    public void testOnEvent_whenEvent_thenSendIt() {
        CommandEvent event = Mockito.mock(CommandEvent.class);

        listener.onEvent(event);

        Mockito.verify(session, Mockito.times(1)).send(eq(destination), same(event));
    }
}
