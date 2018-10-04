package com.apexmob.casus.sample.websocket.server;

import com.apexmob.casus.Character;
import com.apexmob.casus.*;
import com.apexmob.casus.character.SimpleCharacter;
import com.apexmob.casus.client.ClientModule;
import com.apexmob.casus.client.UsernamePasswordAuthenticationService;
import com.apexmob.casus.client.stomp.StompClientModule;
import com.apexmob.casus.client.stomp.StompConfigurationService;
import com.apexmob.casus.client.stomp.authn.StompUsernamePasswordAuthenticationModule;
import com.apexmob.casus.command.CommandEvent;
import com.apexmob.casus.command.CommandModule;
import com.apexmob.casus.command.SimpleCommandEvent;
import com.apexmob.casus.sample.SampleEvent;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.TestCase.assertNotNull;

/**
 * @author Chris Kirk
 * @since 1.0
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientIntegrationTest {
    @Value("${local.server.port}")
    private int port;

    private String URL;

    private CompletableFuture<SampleEvent> completableFuture;

    @Before
    public void setup() {
        completableFuture = new CompletableFuture<>();
        URL = "ws://localhost:" + port + "/game";
    }

//    @Test
    public void testCreateGameEndpoint() throws InterruptedException, ExecutionException, TimeoutException {

        String name = "Molly";

        Character character = new SimpleCharacter();
        character.setName(name);

        GameEngine engine = new SimpleGameEngine();
        engine.add(new CommandModule());
        engine.add(new ClientModule());
        engine.add(new StompClientModule());
        engine.add(new SampleStompModule());

        engine.add(new StompUsernamePasswordAuthenticationModule());
        engine.start();

        engine.getGameEventDispatcher(SampleEvent.class).addListener(new TestSampleEventListener());

        StompConfigurationService configurationService = (StompConfigurationService) engine.getService(StompConfigurationService.class.getName());

        UsernamePasswordAuthenticationService authenticationService = (UsernamePasswordAuthenticationService) engine.getService(UsernamePasswordAuthenticationService.class.getName());
        authenticationService.authenticate(name, "password");




        GameEventDispatcher<CommandEvent> eventDispatcher = (GameEventDispatcher<CommandEvent>)engine.getGameEventDispatcher(CommandEvent.class);
        eventDispatcher.dispatch(new SimpleCommandEvent(character, "sample"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        authenticationService.disconnect(name);

        engine.stop();


        SampleEvent sampleEvent = completableFuture.get(10, TimeUnit.SECONDS);

        assertNotNull(sampleEvent);
    }


//    @Test
    public void testCreateGameEndpoint2() throws InterruptedException, ExecutionException, TimeoutException {

        String name = "Molly";

        Character character = new SimpleCharacter();
        character.setName(name);

        GameEngine engine = new SimpleGameEngine();
        engine.add(new CommandModule());
        engine.add(new ClientModule());
        engine.add(new StompClientModule());
        engine.add(new SampleStompModule());

        engine.add(new StompUsernamePasswordAuthenticationModule());
        engine.start();

        engine.getGameEventDispatcher(SampleEvent.class).addListener(new TestSampleEventListener());

        StompConfigurationService configurationService = (StompConfigurationService) engine.getService(StompConfigurationService.class.getName());

        UsernamePasswordAuthenticationService authenticationService = (UsernamePasswordAuthenticationService) engine.getService(UsernamePasswordAuthenticationService.class.getName());
        authenticationService.authenticate(name, "password");




        GameEventDispatcher<CommandEvent> eventDispatcher = (GameEventDispatcher<CommandEvent>)engine.getGameEventDispatcher(CommandEvent.class);
        eventDispatcher.dispatch(new SimpleCommandEvent(character, "sample"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        authenticationService.disconnect(name);

        engine.stop();


        SampleEvent sampleEvent = completableFuture.get(10, TimeUnit.SECONDS);

        assertNotNull(sampleEvent);
    }

    private class TestSampleEventListener implements GameEventListener<SampleEvent> {
        @Override
        public boolean onEvent(SampleEvent event) {
            completableFuture.complete(event);
            return true;
        }
    }

}
