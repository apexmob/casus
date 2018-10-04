package com.apexmob.casus.client.stomp;

import com.apexmob.casus.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class StompClientModuleTest {

    private StompClientModule module;

    @Before
    public void before() {
        module = new StompClientModule();
    }

    @Test
    public void testStart_whenStompConfigurationServiceNotPresent_thenPopulateStompConfigurationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(null);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(1)).putService(eq(StompConfigurationService.class.getName()), any(StompConfigurationService.class));
    }

    @Test
    public void testStart_whenStompConfigurationServicePresent_thenDoNotPopulateStompConfigurationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        StompConfigurationService configurationService = Mockito.mock(StompConfigurationService.class);

        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(configurationService);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(StompConfigurationService.class.getName()), any(StompConfigurationService.class));
    }

    @Test
    public void testStop_whenStompConfigurationServiceNotPresent_thenRemoveStompConfigurationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(null);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(1)).removeService(eq(StompConfigurationService.class.getName()));
    }

    @Test
    public void testStop_whenStompConfigurationServicePresent_thenDoNotRemoveStompConfigurationService() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        StompConfigurationService configurationService = Mockito.mock(StompConfigurationService.class);

        Mockito.when(engine.getService(eq(StompConfigurationService.class.getName()))).thenReturn(configurationService);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(0)).removeService(eq(StompConfigurationService.class.getName()));
    }
}
