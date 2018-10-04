package com.apexmob.casus.client;

import com.apexmob.casus.GameEngine;

/**
 * CommandDispatcher represents the basic interface enabling a client to send a command to a remote {@link GameEngine}.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface CommandDispatcher {

    /**
     * Send the provided command.
     * @param command The command to send.
     */
    void sendCommand(String command);

}
