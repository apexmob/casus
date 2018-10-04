package com.apexmob.casus.client.stomp.authn;

import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.client.GameEventDistributor;
import com.apexmob.casus.client.UsernamePasswordAuthenticationService;
import com.apexmob.casus.client.stomp.StompConfigurationService;
import com.apexmob.casus.command.CommandEvent;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.util.Base64;
import java.util.Hashtable;
import java.util.Map;

/**
 * StompUsernamePasswordAuthenticationService is a concrete implementation of {@link UsernamePasswordAuthenticationService}
 * that authenticates to a Stomp web socket.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class StompUsernamePasswordAuthenticationService extends StompAuthenticationService implements UsernamePasswordAuthenticationService {

    private Map<String, StompSession> sessionMap = new Hashtable<>();

    /**
     * {@inheritDoc}
     */
    public StompUsernamePasswordAuthenticationService(StompConfigurationService configurationService, GameEventDistributor distributor, GameEventDispatcher<CommandEvent> manager) {
        super(configurationService, distributor, manager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean authenticate(String username, String password) {
        boolean retVal = false;

        String plainCredentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);

        StompSession stompSession = connect(headers);
        if (stompSession != null) {
            sessionMap.put(username, stompSession);
            retVal = true;
        }

        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect(String username) {
        StompSession stompSession = sessionMap.get(username);
        if (stompSession != null) {
            //TODO add logging
            stompSession.disconnect();
            sessionMap.remove(username);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isConnected(String username) {
        StompSession stompSession = sessionMap.get(username);
        return stompSession != null && stompSession.isConnected();
    }

}
