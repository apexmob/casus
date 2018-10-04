package com.apexmob.casus;

/**
 * Service represents the basic interface for all services executing within the game engine.  At this time,
 * it simply serves as a marker interface.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface Module {

    /**
     * Register the module functionality within the provided game engine.
     * @param gameEngine The game engine in which to register the module functionality.
     */
    void start(GameEngine gameEngine);

    /**
     * Clean up and object resources with references to capabilities registered in the game engine by the module.
     * @param gameEngine The game engine in which to clean up the module functionality.
     */
    void stop(GameEngine gameEngine);
}
