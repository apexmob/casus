package com.apexmob.casus.client;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.GameEventDispatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.same;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleGameEventDistributorTest {

    private SimpleGameEventDistributor distributor;

    @Before
    public void before() {
        distributor = new SimpleGameEventDistributor();
    }

    @Test
    public void testDistribute_whenDispatcherSet_thenEventsDistributed() {
        GameEventDispatcher dispatcher = Mockito.mock(GameEventDispatcher.class);
        MockEvent event = Mockito.mock(MockEvent.class);

        distributor.putGameEventDispatcher(MockEvent.class, dispatcher);

        distributor.distribute(MockEvent.class, event);

        Mockito.verify(dispatcher, Mockito.times(1)).dispatch(same(event));
    }

    @Test
    public void testRemoveGameEventDispatcher_whenDispatcherSet_thenReturnDispatcher() {
        GameEventDispatcher dispatcher = Mockito.mock(GameEventDispatcher.class);

        distributor.putGameEventDispatcher(MockEvent.class, dispatcher);

        GameEventDispatcher removed = distributor.removeGameEventDispatcher(MockEvent.class);

        assertSame(dispatcher, removed);
    }

    @Test
    public void testDistribute_whenDispatcherNotSet_thenEventsNotDistributed() {
        MockEvent event = Mockito.mock(MockEvent.class);

        distributor.distribute(MockEvent.class, event);

        //Successfully ignored event
        assertTrue(true);
    }

    @Test
    public void testRemoveGameEventDispatcher_whenDispatcherNotSet_thenReturnNull() {
        GameEventDispatcher removed = distributor.removeGameEventDispatcher(MockEvent.class);

        assertNull(removed);
    }

    private class MockEvent implements GameEvent {

    }
}
