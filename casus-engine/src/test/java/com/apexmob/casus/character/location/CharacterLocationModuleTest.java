package com.apexmob.casus.character.location;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class CharacterLocationModuleTest {

    private CharacterLocationModule module;

    @Before
    public void before() {
        module = new CharacterLocationModule();
    }

    @Test
    public void testStart_whenCharacterLocationServiceNotPresent_thenPopulateCharacterLocationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(null);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(1)).putService(eq(CharacterLocationService.class.getName()), any(CharacterLocationService.class));
    }

    @Test
    public void testStart_whenCharacterLocationServicePresent_thenDoNotPopulateCharacterLocationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        CharacterLocationService service = Mockito.mock(CharacterLocationService.class);

        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(service);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(CharacterLocationService.class.getName()), any(CharacterLocationService.class));
    }

    @Test
    public void testStop_whenCharacterLocationServiceNotPresent_thenRemoveCharacterLocationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(null);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(1)).removeService(eq(CharacterLocationService.class.getName()));
    }

    @Test
    public void testStop_whenCharacterLocationServicePresent_thenDoNotRemoveCharacterLocationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        CharacterLocationService service = Mockito.mock(CharacterLocationService.class);

        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(service);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(0)).removeService(eq(CharacterLocationService.class.getName()));
    }

    @Test
    public void testStart_whenGameEventDispatcherNotPresent_thenPopulateGameEventDispatcher() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getGameEventDispatcher(eq(CharacterLocationChangedEvent.class))).thenReturn(null);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(1)).putGameEventDispatcher(eq(CharacterLocationChangedEvent.class), any(GameEventDispatcher.class));
    }

    @Test
    public void testStart_whenGameEventDispatcherPresent_thenDoNotPopulateGameEventDispatcher() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        GameEventDispatcher dispatcher = Mockito.mock(GameEventDispatcher.class);

        Mockito.when(engine.getGameEventDispatcher(eq(CharacterLocationChangedEvent.class))).thenReturn(dispatcher);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putGameEventDispatcher(eq(CharacterLocationChangedEvent.class), any(GameEventDispatcher.class));
    }

    @Test
    public void testStop_whenGameEventDispatcherNotPresent_thenRemoveGameEventDispatcher() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getGameEventDispatcher(eq(CharacterLocationChangedEvent.class))).thenReturn(null);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(1)).removeGameEventDispatcher(eq(CharacterLocationChangedEvent.class));
    }

    @Test
    public void testStop_whenGameEventDispatcherPresent_thenDoNotRemoveGameEventDispatcher() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        GameEventDispatcher dispatcher = Mockito.mock(GameEventDispatcher.class);

        Mockito.when(engine.getGameEventDispatcher(eq(CharacterLocationChangedEvent.class))).thenReturn(dispatcher);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(0)).removeGameEventDispatcher(eq(CharacterLocationChangedEvent.class));
    }
}
