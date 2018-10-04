package com.apexmob.casus;

/**
 * GameEngine represents the core interface through which functionality can be added to the engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface GameEngine {

    /**
     * Add a module to the engine.
     * @param module The module to be added.
     * @return True if successfully added, otherwise false.
     */
    boolean add(Module module);

    /**
     * Remove a module from the engine.
     * @param module The module to be removed.
     * @return True if the removal resulted in a change to the modules within the engine, otherwise false.
     */
    boolean remove(Module module);

    /**
     * Register a service in the game engine.
     * @param name The name by which the service is referenced.  Typically, this would be through the class name of an
     *             interface class (rather than a concrete implementation class name) in order to support switchin implementations.
     * @param service The service instance.
     */
    void putService(String name, Service service);

    /**
     * Remove a service from the game engine.
     * @param name The name by which the service is referenced.  Typically, this would be through the class name of an
     *             interface class (rather than a concrete implementation class name) in order to support switchin implementations.
     * @return The service if found in the game engine, otherwise null.
     */
    Service removeService(String name);

    /**
     * Retrieve the service with the specified name.
     * @param name The name by which the service is referenced.  Typically, this would be through the class name of an
     *             interface class (rather than a concrete implementation class name) in order to support switchin implementations.
     * @return The service if found in the game engine, otherwise null.
     */
    Service getService(String name);

    /**
     * Start the engine.  Starting the engine will start the modules and other associated game components.
     */
    void start();

    /**
     * Stop the engine. Stopping the engine will stoop the modules and other associated game components.
     */
    void stop();

    /**
     * Set an event dispatcher for an event.
     * @param eventClass The class of the event.
     * @param eventDispatcher The event dispatcher for dispatching events of that type.
     */
    void putGameEventDispatcher(Class eventClass, GameEventDispatcher eventDispatcher);

    /**
     * Remove an event dispatcher for an event.
     * @param eventClass The class of the event.
     * @return The event dispatcher for dispatching events of that type if removed, otherwise null.
     */
    GameEventDispatcher removeGameEventDispatcher(Class eventClass);

    /**
     * Retrieve an event dispatcher for an event.
     * @param eventClass The class of the event.
     * @return The event dispatcher for dispatching events of that type if found, otherwise null.
     */
    GameEventDispatcher getGameEventDispatcher(Class eventClass);
}
