package com.apexmob.casus.client;

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
public class ClientModuleTest {

    private ClientModule module;

    @Before
    public void before() {
        module = new ClientModule();
    }

    @Test
    public void testStart_whenDistributorNotPresent_thenPopulateDistributor() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(null);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(1)).putService(eq(GameEventDistributor.class.getName()), any(GameEventDistributor.class));
    }

    @Test
    public void testStart_whenDistributorPresent_thenDoNotPopulateDistributor() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        GameEventDistributor distributor = Mockito.mock(GameEventDistributor.class);

        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(distributor);

        module.start(engine);

        Mockito.verify(engine, Mockito.times(0)).putService(eq(GameEventDistributor.class.getName()), any(GameEventDistributor.class));
    }

    @Test
    public void testStop_whenDistributorNotPresent_thenRemoveDistributor() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(null);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(1)).removeService(eq(GameEventDistributor.class.getName()));
    }

    @Test
    public void testStop_whenDistributorPresent_thenDoNotRemoveDistributor() {
        GameEngine engine = Mockito.mock(GameEngine.class);
        GameEventDistributor distributor = Mockito.mock(GameEventDistributor.class);

        Mockito.when(engine.getService(eq(GameEventDistributor.class.getName()))).thenReturn(distributor);

        module.start(engine);
        module.stop(engine);

        Mockito.verify(engine, Mockito.times(0)).removeService(eq(GameEventDistributor.class.getName()));
    }
}
