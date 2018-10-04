package com.apexmob.casus;

/**
 * GameObjectEvent represents the basic interface for all events within the game engine that are directly related to a single
 * game object.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface GameObjectEvent<T> extends GameEvent {

    /**
     * Retreive the game object to which the event is associated.
     * @return The game object.
     */
    T getGameObject();

}
