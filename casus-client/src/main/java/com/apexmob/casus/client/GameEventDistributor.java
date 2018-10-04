package com.apexmob.casus.client;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.Service;

import java.lang.reflect.Type;

/**
 * GameEventDistributor represents the basic interface for distributing events received from a remote system to
 * the appropriate {@link GameEventDispatcher}.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface GameEventDistributor extends Service {

    /**
     * Distribute the provided event to the dispatcher that manages the provided type.
     * @param type The {@link Type} associated with the {@link GameEventDispatcher}.
     * @param event The event to distribute.
     */
    void distribute(Type type, GameEvent event);

    /**
     * Register a {@link GameEventDispatcher} for the provided {@link Type}.
     * @param type The event {@link Type}.
     * @param eventDispatcher The dispatcher.
     */
    void putGameEventDispatcher(Type type, GameEventDispatcher eventDispatcher);

    /**
     * Remove a {@link GameEventDispatcher} for the provided {@link Type}.
     * @param type The event {@link Type}.
     * @return The dispatcher if present and removed, otherwise null.
     */
    GameEventDispatcher removeGameEventDispatcher(Type type);
}

