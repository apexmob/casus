package com.apexmob.casus.client.stomp.authn;

//TODO add tests

import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.client.GameEventDistributor;
import com.apexmob.casus.client.stomp.StompConfigurationService;
import com.apexmob.casus.client.stomp.WebSocketContainerProvider;
import com.apexmob.casus.command.CommandEvent;
import com.apexmob.casus.net.UriResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.websocket.ContainerProvider;
import java.net.URI;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Chris Kirk
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StompUsernamePasswordAuthenticationServiceTest {

    @Value("${local.server.port}")
    private int port;

    private StompConfigurationService configurationService;
    private GameEventDistributor distributor;
    private GameEventDispatcher dispatcher;
    private UriResolver uriResolver;
    private WebSocketContainerProvider containerProvider;

    private StompUsernamePasswordAuthenticationService service;



    @Before
    public void before() {
        configurationService = Mockito.mock(StompConfigurationService.class);
        distributor = Mockito.mock(GameEventDistributor.class);
        dispatcher = Mockito.mock(GameEventDispatcher.class);
        uriResolver = Mockito.mock(UriResolver.class);
        containerProvider = Mockito.mock(WebSocketContainerProvider.class);

        when(configurationService.getUriResolver()).thenReturn(uriResolver);
        when(configurationService.getWebSocketContainerProvider()).thenReturn(containerProvider);
        when(uriResolver.resolveUri()).thenReturn(URI.create("ws://localhost:" + port + "/test"));
        when(containerProvider.getWebSocketContainer()).thenReturn(ContainerProvider.getWebSocketContainer());

        service = new StompUsernamePasswordAuthenticationService(configurationService, distributor, dispatcher);
    }

    @After
    public void after() {
        service.disconnect("John");
    }

    @Test
    public void testAuthenticate_whenValidCredentials_thenReturnTrue() {
        assertTrue(service.authenticate("John", "password"));
    }

    @Test
    public void testAuthenticate_whenInvalidCredentials_thenReturnFalse() {
        assertFalse(service.authenticate("Jake", "password"));
    }

    @Test
    public void testIsConnected_whenValidCredentials_thenReturnTrue() {
        service.authenticate("John", "password");
        assertTrue(service.isConnected("John"));
    }

    @Test
    public void testIsConnected_whenNoSession_thenReturnFalse() {
        assertFalse(service.isConnected("Fail"));
    }

    @Test
    public void testIsConnected_whenDisconnected_thenReturnFalse() {
        service.authenticate("John", "password");
        service.disconnect("John");
        assertFalse(service.isConnected("John"));
    }

    @Test
    public void testDisconnect_whenSessionPresent_thenDisconnectSession() {
        service.authenticate("John", "password");
        service.disconnect("John");
    }

    @Test
    public void testDisconnect_whenSessionNotPresent_thenDoNothing() {
        service.disconnect("Jake");
    }
}
