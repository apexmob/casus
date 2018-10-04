package com.apexmob.casus.client.stomp.authn;

import com.apexmob.casus.BaseService;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.client.GameEventDistributor;
import com.apexmob.casus.client.stomp.*;
import com.apexmob.casus.command.CommandEvent;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.websocket.WebSocketContainer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * StompAuthenticationService is an Abstract class that contains authentication functionality useful for
 * implementing various authentication approaches for Stomp web sockets.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public abstract class StompAuthenticationService extends BaseService {

    //TODO swap system.out.println for Logger

    private final GameEventDistributor distributor;
    private final GameEventDispatcher<CommandEvent> manager;
    private final StompConfigurationService configurationService;

    /**
     * Construct a new instance.
     * @param configurationService The service providing Stomp configuration.
     * @param distributor The event distributor for events received through the web socket.
     * @param eventDispatcher The command event dispatcher through which commands are dispatched.
     */
    protected StompAuthenticationService(StompConfigurationService configurationService, GameEventDistributor distributor, GameEventDispatcher<CommandEvent> eventDispatcher) {
        this.configurationService = configurationService;
        this.distributor = distributor;
        this.manager = eventDispatcher;
    }

    /**
     * Connect to the web socket with the provided headers.
     * @param headers Header information to be included on the connection request.
     * @return The established Stomp session.
     */
    protected StompSession connect(WebSocketHttpHeaders headers) {
        StompSession retVal = null;

        WebSocketContainer container = configurationService.getWebSocketContainerProvider().getWebSocketContainer();
        System.out.println(container);
        StandardWebSocketClient client = new StandardWebSocketClient(container);
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        StompSessionHandler sessionHandler = new StompSessionManager(distributor, configurationService);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        ListenableFuture<StompSession> future = stompClient.connect(configurationService.getUriResolver().resolveUri(), headers, new StompHeaders(), sessionHandler);

        try {
            retVal = future.get(3, TimeUnit.SECONDS);

            System.out.println("New session established : " + retVal.getSessionId());

            for (String subscription : configurationService.getSubscriptions()) {
                retVal.subscribe(subscription, sessionHandler);
                System.out.println("Subscribed to " + subscription);
            }

            //TODO refactor commands destination into stomp configuration service
            manager.addListener(new StompCommandEventListener(retVal, "/queue/commands"));
        } catch (Exception e) {
            //TODO add logging
            e.printStackTrace();
        }
        return retVal;
    }
}
