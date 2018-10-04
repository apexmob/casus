package com.apexmob.casus.client;

import com.apexmob.casus.GameEvent;
import com.apexmob.casus.GameEventDispatcher;

import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Map;

/**
 * SimpleGameEventDistributor is a basic implementation of the {@link GameEventDistributor} interface.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleGameEventDistributor implements GameEventDistributor {

    private final Map<Type, GameEventDispatcher> eventDispatcherMap = new Hashtable<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void distribute(Type type, GameEvent event) {
        GameEventDispatcher eventDispatcher = eventDispatcherMap.get(type);
        if (eventDispatcher != null) {
            eventDispatcher.dispatch(event);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putGameEventDispatcher(Type type, GameEventDispatcher eventDispatcher) {
        eventDispatcherMap.put(type, eventDispatcher);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEventDispatcher removeGameEventDispatcher(Type type) {
        return eventDispatcherMap.remove(type);
    }
}

