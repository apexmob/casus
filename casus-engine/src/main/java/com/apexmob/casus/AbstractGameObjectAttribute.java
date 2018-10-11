package com.apexmob.casus;

/**
 * AbstractGameObjectAttribute is an abstract implementation of the {@link GameObjectAttribute} that provides
 * standard {@link GameObjectAttribute} functionality that will be necessary across all {@link GameObjectAttribute}
 * implementations.
 *
 * @author Chris Kirk
 * @since 1.0
 * @param <T> The type of object held by the attribute.
 */
public abstract class AbstractGameObjectAttribute<T extends GameObject> implements GameObjectAttribute<T> {

    private T gameObject;

    /**
     * {@inheritDoc}
     */
    @Override
    public T getGameObject() {
        return gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameObject(T gameObject) {
        this.gameObject = gameObject;
    }
}
