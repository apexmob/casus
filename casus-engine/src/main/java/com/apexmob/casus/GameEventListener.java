package com.apexmob.casus;

/**
 * GameEventListener represents the basic interface for all event listeners within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface GameEventListener<T extends GameEvent> {

    /**
     * Perform work necessary based on the provided event.
     * @param event The event that has occurred.
     * @return True if the event was acted upon, otherwise false.
     */
    boolean onEvent(T event);
}
