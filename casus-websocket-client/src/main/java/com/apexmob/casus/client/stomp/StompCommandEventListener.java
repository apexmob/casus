package com.apexmob.casus.client.stomp;

import com.apexmob.casus.GameEventListener;
import com.apexmob.casus.command.CommandEvent;
import org.springframework.messaging.simp.stomp.StompSession;

/**
 * StompCommandEventListener is an event listener that listens for {@link CommandEvent} events and communicates
 * them to a command destination within a stomp web socket.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class StompCommandEventListener implements GameEventListener<CommandEvent> {

    private final StompSession session;
    private final String destination;

    /**
     * Construct a new instance.
     * @param session The established stomp session.
     * @param destination The stomp destination.
     */
    public StompCommandEventListener(StompSession session, String destination) {
        this.session = session;
        this.destination = destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onEvent(CommandEvent event) {
        session.send(destination, event);
        return true;
    }
}
