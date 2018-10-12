package com.apexmob.casus.attribute.event;

import com.apexmob.casus.GameEvent;

/**
 * ObjectListChangedEvent represents an event that signifies that a change to a list has occurred.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface ObjectListChangedEvent<T> extends GameEvent {

    /**
     * Retrieve the object that was added/removed within the list.
     * @return The object.
     */
    T getObject();

    /**
     * Retrieve the type of change that occurred in the list, addition or removal.
     * @return The type of change.
     */
    ChangeType getChangeType();

    /**
     * ChangeType represents the type of change that occurred on the list, addition or removal.
     */
    static enum ChangeType {
        ADDITION,
        REMOVAL
    }
}
