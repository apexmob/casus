package com.apexmob.casus.client;

import com.apexmob.casus.Service;

/**
 * UsernamePasswordAuthenticationService represents the interface for a service that provides user authentication
 * to a remote system.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface UsernamePasswordAuthenticationService extends Service {

    /**
     * Authenticate the user to the remote system.
     * @param username The user's username.
     * @param password The user's password.
     * @return True if authentication is successful, otherwise false.
     */
    boolean authenticate(String username, String password);

    /**
     * End the user's interaction with the remote system.
     * @param username The user's username.
     */
    void disconnect(String username);

    /**
     * Check if the provided user is currently connected.
     * @param username The username of the user.
     * @return True if the user is connected, otherwise false.
     */
    boolean isConnected(String username);
}
