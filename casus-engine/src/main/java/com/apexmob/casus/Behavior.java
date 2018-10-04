package com.apexmob.casus;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

//TODO docs
/**
 * Behavior represents the basic interface for applying custom functionality to an object in the engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface Behavior<T> {

    /**
     * Add functionality to the provided object.
     * @param gameEngine The game engine.
     * @param obj The object to which functionality should be added.
     */
    void start(GameEngine gameEngine, T obj);

    /**
     * Remove functionality from the provided object.
     * @param gameEngine The game engine.
     * @param obj The object from which functionality should be removed.
     */
    void stop(GameEngine gameEngine, T obj);
}
