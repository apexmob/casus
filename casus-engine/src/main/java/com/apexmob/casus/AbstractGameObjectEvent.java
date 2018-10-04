package com.apexmob.casus;

/**
 * AbstractGameObjectEvent is an abstract implementation of the {@link GameObjectEvent} interface that provides
 * standard {@link GameObjectEvent} that will be necessary across all {@link GameObjectEvent} implementations.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class AbstractGameObjectEvent<T extends GameObject> implements GameObjectEvent<T> {

    private final T gameObject;

    /**
     * Construct an instance with the provided game object.
     * @param gameObject The game object associated with the event.
     */
    protected AbstractGameObjectEvent(T gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getGameObject() {
        return gameObject;
    }
}
