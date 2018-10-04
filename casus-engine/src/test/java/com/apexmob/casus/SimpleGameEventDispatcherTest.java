package com.apexmob.casus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.same;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleGameEventDispatcherTest {

    private SimpleGameEventDispatcher dispatcher;
    private GameEventListener listener;
    private GameEvent event;

    @Before
    public void before() {
        dispatcher = new SimpleGameEventDispatcher();

        listener = Mockito.mock(GameEventListener.class);
        event = Mockito.mock(GameEvent.class);
    }

    @Test
    public void testDispatch_whenListenerAdded_thenListenerReceiveEvent() {
        dispatcher.addListener(listener);
        dispatcher.dispatch(event);

        Mockito.verify(listener, Mockito.times(1)).onEvent(same(event));
    }

    @Test
    public void testDispatch_whenListenerRemoved_thenListenerDoesNotReceiveEvent() {
        dispatcher.addListener(listener);
        dispatcher.removeListener(listener);
        dispatcher.dispatch(event);

        Mockito.verifyZeroInteractions(listener);
    }
}
