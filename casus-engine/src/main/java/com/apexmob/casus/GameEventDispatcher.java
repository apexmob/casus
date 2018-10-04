package com.apexmob.casus;

/**
 * GameEventDispatcher represents the basic interface for classes that dispatch game events to event listeners.  It
 * provides methods for adding/removing event listeners as well as dispatching events to those listeners.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface GameEventDispatcher<T extends GameEvent> {

    /**
     * Send the provided event to all registered event listeners.
     * @param event The event to send.
     * @return True if the event was handled, otherwise false.
     */
    boolean dispatch(T event);

    /**
     * Register an event listener.
     * @param listener The event listener to add.
     */
    void addListener(GameEventListener<T> listener);

    /**
     * Remove the provided event listener.
     * @param listener The event listener to remove.
     * @return True if the listener was found and removed, otherwise false.
     */
    boolean removeListener(GameEventListener<T> listener);
}
