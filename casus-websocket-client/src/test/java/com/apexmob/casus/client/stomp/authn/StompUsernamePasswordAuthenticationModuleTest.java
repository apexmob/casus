package com.apexmob.casus.client.stomp.authn;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.client.GameEventDistributor;
import com.apexmob.casus.client.UsernamePasswordAuthenticationService;
import com.apexmob.casus.client.stomp.StompConfigurationService;
import com.apexmob.casus.command.CommandEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class StompUsernamePasswordAuthenticationModuleTest {

    private StompUsernamePasswordAuthenticationModule module;

    @Before
    public void before() {
        module = new StompUsernamePasswordAuthenticationModule();
    }

    @Test
    public void testStart_whenStompConfigurationServiceNotPresent_thenDoNotPopulateUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(Mockito.mock(GameEventDistributor.class));
        Mockito.when(engine.getGameEventDispatcher(eq(CommandEvent.class))).thenReturn(Mockito.mock(GameEventDispatcher.class));

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(UsernamePasswordAuthenticationService.class.getName()), any(UsernamePasswordAuthenticationService.class));
    }

    @Test
    public void testStart_whenGameEventDistributorNotPresent_thenDoNotPopulateUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(Mockito.mock(StompConfigurationService.class));
        Mockito.when(engine.getGameEventDispatcher(eq(CommandEvent.class))).thenReturn(Mockito.mock(GameEventDispatcher.class));

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(UsernamePasswordAuthenticationService.class.getName()), any(UsernamePasswordAuthenticationService.class));
    }

    @Test
    public void testStart_whengetGameEventDispatcherNotPresent_thenDoNotPopulateUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(Mockito.mock(StompConfigurationService.class));
        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(Mockito.mock(GameEventDistributor.class));

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(UsernamePasswordAuthenticationService.class.getName()), any(UsernamePasswordAuthenticationService.class));
    }

    @Test
    public void testStart_whenUsernamePasswordAuthenticationServiceNotPresent_thenPopulateUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(Mockito.mock(StompConfigurationService.class));
        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(Mockito.mock(GameEventDistributor.class));
        Mockito.when(engine.getGameEventDispatcher(eq(CommandEvent.class))).thenReturn(Mockito.mock(GameEventDispatcher.class));

        module.start(engine);

        Mockito.verify(engine, Mockito.times(1)).putService(eq(UsernamePasswordAuthenticationService.class.getName()), any(UsernamePasswordAuthenticationService.class));
    }

    @Test
    public void testStart_whenUsernamePasswordAuthenticationServicePresent_thenDoNotPopulateUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        UsernamePasswordAuthenticationService configurationService = Mockito.mock(UsernamePasswordAuthenticationService.class);

        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(configurationService);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(UsernamePasswordAuthenticationService.class.getName()), any(UsernamePasswordAuthenticationService.class));
    }

    @Test
    public void testStop_whenUsernamePasswordAuthenticationServiceNotPresent_thenRemoveUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(null);
        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(Mockito.mock(StompConfigurationService.class));
        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(Mockito.mock(GameEventDistributor.class));
        Mockito.when(engine.getGameEventDispatcher(eq(CommandEvent.class))).thenReturn(Mockito.mock(GameEventDispatcher.class));

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(1)).removeService(eq(UsernamePasswordAuthenticationService.class.getName()));
    }

    @Test
    public void testStop_whenUsernamePasswordAuthenticationServicePresent_thenDoNotRemoveUsernamePasswordAuthenticationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        UsernamePasswordAuthenticationService configurationService = Mockito.mock(UsernamePasswordAuthenticationService.class);

        Mockito.when(engine.getService(eq(UsernamePasswordAuthenticationService.class.getName()))).thenReturn(configurationService);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(0)).removeService(eq(UsernamePasswordAuthenticationService.class.getName()));
    }
}
