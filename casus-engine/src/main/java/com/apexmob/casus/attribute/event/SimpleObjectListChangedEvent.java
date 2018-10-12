package com.apexmob.casus.attribute.event;

import com.apexmob.casus.AbstractGameObjectEvent;
import com.apexmob.casus.GameObject;

/**
 * SimpleObjectListChangedEvent is a simple implementation of the {@link ObjectListChangedEvent} interface.
 * events.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleObjectListChangedEvent<T> extends AbstractGameObjectEvent implements ObjectListChangedEvent<T> {

    private final T obj;
    private final ChangeType changeType;

    /**
     * Construct a new instance.
     * @param gameObject The game object to which the event is associated.
     * @param obj The object that was added/removed within the list.
     * @param changeType The type of change that occurred in the list, addition or removal.
     */
    public SimpleObjectListChangedEvent(GameObject gameObject, T obj, ChangeType changeType) {
        super(gameObject);
        this.obj = obj;
        this.changeType = changeType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getObject() {
        return obj;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChangeType getChangeType() {
        return changeType;
    }
}
