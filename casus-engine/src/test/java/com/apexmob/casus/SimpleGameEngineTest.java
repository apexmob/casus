package com.apexmob.casus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleGameEngineTest {

    private SimpleGameEngine engine;
    private Service service;
    private Module module;
    private GameEventDispatcher dispatcher;

    @Before
    public void before() {
        engine = new SimpleGameEngine();

        service = Mockito.mock(Service.class);
        module = Mockito.mock(Module.class);
        dispatcher = Mockito.mock(GameEventDispatcher.class);
    }

    @Test
    public void testGetService_whenNoService_thenReturnNull() {
        assertNull(engine.getService("fail"));
    }

    @Test
    public void testGetService_whenService_thenReturnService() {
        engine.putService("test", service);
        assertSame(service, engine.getService("test"));
    }

    @Test
    public void testPutService_whenServicePresent_thenReplaceExisting() {
        Service old = Mockito.mock(Service.class);
        engine.putService("test", old);
        engine.putService("test", service);

        assertSame(service, engine.getService("test"));
    }

    @Test
    public void testRemoveService_whenNoService_thenReturnNull() {
        assertNull(engine.removeService("fail"));
    }

    @Test
    public void testRemoveService_whenService_thenReturnService() {
        engine.putService("test", service);
        assertSame(service, engine.removeService("test"));
    }

    @Test
    public void testRemoveService_whenService_thenServiceRemoved() {
        engine.putService("test", service);
        engine.removeService("test");

        assertNull(engine.getService("test"));
    }

    @Test
    public void testStart_whenModuleAdded_thenModuleStarted() {
        engine.add(module);

        engine.start();

        Mockito.verify(module, times(1)).start(same(engine));
    }

    @Test
    public void testStart_whenModuleRemoved_thenModuleNotStarted() {
        engine.add(module);
        engine.remove(module);

        engine.start();

        Mockito.verify(module, times(0)).start(same(engine));
    }

    @Test
    public void testStop_whenModuleAdded_thenModuleStopped() {
        engine.add(module);

        engine.stop();

        Mockito.verify(module, times(1)).stop(same(engine));
    }

    @Test
    public void testStop_whenModuleRemoved_thenModuleNotStopped() {
        engine.add(module);
        engine.remove(module);

        engine.stop();

        Mockito.verify(module, times(0)).stop(same(engine));
    }

    @Test
    public void testGetGameEventDispatcher_whenNoGameEventDispatcher_thenReturnNull() {
        assertNull(engine.getGameEventDispatcher(GameEvent.class));
    }

    @Test
    public void testGetGameEventDispatcher_whenGameEventDispatcher_thenReturnGameEventDispatcher() {
        engine.putGameEventDispatcher(GameEvent.class, dispatcher);
        assertSame(dispatcher, engine.getGameEventDispatcher(GameEvent.class));
    }

    @Test
    public void testPutGameEventDispatcher_whenGameEventDispatcherPresent_thenReplaceExisting() {
        GameEventDispatcher old = Mockito.mock(GameEventDispatcher.class);
        engine.putGameEventDispatcher(GameEvent.class, old);

        engine.putGameEventDispatcher(GameEvent.class, dispatcher);
        assertSame(dispatcher, engine.getGameEventDispatcher(GameEvent.class));
    }

    @Test
    public void testRemoveGameEventDispatcher_whenNoGameEventDispatcher_thenReturnNull() {
        assertNull(engine.removeGameEventDispatcher(GameEvent.class));
    }

    @Test
    public void testRemoveGameEventDispatcher_whenGameEventDispatcher_thenReturnGameEventDispatcher() {
        engine.putGameEventDispatcher(GameEvent.class, dispatcher);
        assertSame(dispatcher, engine.removeGameEventDispatcher(GameEvent.class));
    }

    @Test
    public void testRemoveGameEventDispatcher_whenGameEventDispatcher_thenGameEventDispatcherRemoved() {
        engine.putGameEventDispatcher(GameEvent.class, dispatcher);
        engine.removeGameEventDispatcher(GameEvent.class);

        assertNull(engine.getGameEventDispatcher(GameEvent.class));
    }

    @Test
    public void testIsRunning_whenNotStarted_thenReturnFalse() {
        assertFalse(engine.isRunning());
    }

    @Test
    public void testIsRunning_whenStarted_thenReturnTrue() {
        engine.start();
        assertTrue(engine.isRunning());
    }

    @Test
    public void testIsRunning_whenStopped_thenReturnFalse() {
        engine.start();
        engine.stop();
        assertFalse(engine.isRunning());
    }
}
