package com.apexmob.casus.action.move;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.character.location.CharacterLocationService;
import com.apexmob.casus.command.CommandAliasor;
import com.apexmob.casus.command.CommandService;
import com.apexmob.casus.command.event.PostAliasingCommandEvent;
import com.apexmob.casus.location.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class MoveActionModuleTest {

    private MoveActionModule module;

    private GameEngine engine;
    private CommandService commandService;
    private LocationService locationService;
    private CharacterLocationService characterLocationService;
    private GameEventDispatcher<PostAliasingCommandEvent> dispatcher;

    @Before
    public void before() {
        module = new MoveActionModule();

        engine = Mockito.mock(GameEngine.class);
        commandService = Mockito.mock(CommandService.class);
        locationService = Mockito.mock(LocationService.class);
        characterLocationService = Mockito.mock(CharacterLocationService.class);
        dispatcher = Mockito.mock(GameEventDispatcher.class);
    }

    @Test
    public void testStart_whenCommandServicePresent_thenPopulateAliasor() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(CommandService.class.getName()))).thenReturn(commandService);

        module.start(engine);

        Mockito.verify(commandService, Mockito.times(1)).addCommandAliasor(any(CommandAliasor.class));
    }

    @Test
    public void testStart_whenCommandServiceNotPresent_thenDoNotPopulateAliasor() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(CommandService.class.getName()))).thenReturn(null);

        module.start(engine);

        Mockito.verify(commandService, Mockito.times(0)).addCommandAliasor(any(CommandAliasor.class));
    }

    @Test
    public void testStop_whenCommandServicePresent_thenRemoveAliasor() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(CommandService.class.getName()))).thenReturn(commandService);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(commandService, Mockito.times(1)).removeCommandAliasor(any(CommandAliasor.class));
    }

    @Test
    public void testStop_whenCommandServiceNotPresent_thenDoNotRemoveAliasor() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(CommandService.class.getName()))).thenReturn(null);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(commandService, Mockito.times(0)).removeCommandAliasor(any(CommandAliasor.class));
    }

    @Test
    public void testStart_whenLocationServiceNotPresent_thenDoNotPopulateMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(characterLocationService);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(dispatcher);

        module.start(engine);

        Mockito.verify(dispatcher, Mockito.times(0)).addListener(any(MoveListener.class));
    }

    @Test
    public void testStart_whenCharacterLocationServiceNotPresent_thenDoNotPopulateMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(locationService);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(dispatcher);

        module.start(engine);

        Mockito.verify(dispatcher, Mockito.times(0)).addListener(any(MoveListener.class));
    }

    @Test
    public void testStart_whenPostAliasingCommandEventDispatcherNotPresent_thenDoNotPopulateMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(locationService);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(characterLocationService);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(null);

        module.start(engine);

        Mockito.verify(dispatcher, Mockito.times(0)).addListener(any(MoveListener.class));
    }

    @Test
    public void testStart_whenDependenciesPresent_thenPopulateMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(locationService);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(characterLocationService);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(dispatcher);

        module.start(engine);

        Mockito.verify(dispatcher, Mockito.times(1)).addListener(any(MoveListener.class));
    }

    @Test
    public void testStop_whenLocationServiceNotPresent_thenDoNotRemoveMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(characterLocationService);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(dispatcher);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(dispatcher, Mockito.times(0)).removeListener(any(MoveListener.class));
    }

    @Test
    public void testStop_whenCharacterLocationServiceNotPresent_thenDoNotRemoveMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(locationService);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(dispatcher);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(dispatcher, Mockito.times(0)).removeListener(any(MoveListener.class));
    }

    @Test
    public void testStop_whenPostAliasingCommandEventDispatcherNotPresent_thenDoNotRemoveMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(locationService);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(characterLocationService);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(null);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(dispatcher, Mockito.times(0)).removeListener(any(MoveListener.class));
    }

    @Test
    public void testStop_whenDependenciesPresent_thenRemoveMoveListener() {
        GameEngine engine = Mockito.mock(GameEngine.class);

        Mockito.when(engine.getService(eq(LocationService.class.getName()))).thenReturn(locationService);
        Mockito.when(engine.getService(eq(CharacterLocationService.class.getName()))).thenReturn(characterLocationService);
        Mockito.when(engine.getGameEventDispatcher(eq(PostAliasingCommandEvent.class))).thenReturn(dispatcher);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(dispatcher, Mockito.times(1)).removeListener(any(MoveListener.class));
    }

}
