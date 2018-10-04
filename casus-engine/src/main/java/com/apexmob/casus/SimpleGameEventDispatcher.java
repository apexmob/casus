package com.apexmob.casus;

import java.util.ArrayList;
import java.util.List;

/**
 * SimpleGameEventDispatcher provides a simple implementation of {@link GameEventDispatcher}.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleGameEventDispatcher<T extends GameEvent> implements GameEventDispatcher<T>, Service {

    private final List<GameEventListener<T>> listeners = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean dispatch(T event) {
        boolean retVal = false;
        for (GameEventListener<T> listener : listeners) {
            retVal = retVal || listener.onEvent(event);
        }
        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addListener(GameEventListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeListener(GameEventListener<T> listener) {
        return listeners.remove(listener);
    }
}
