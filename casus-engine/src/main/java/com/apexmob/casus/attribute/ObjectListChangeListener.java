package com.apexmob.casus.attribute;

import com.apexmob.casus.attribute.event.ObjectListChangedEvent;

/**
 * ObjectListChangeListener represents the basic interface for listening for {@link ObjectListChangedEvent}
 * events.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface ObjectListChangeListener<T> {

    /**
     * Perform work necessary based on the provided event.
     * @param event The event that has occurred.
     */
    void onChange(ObjectListChangedEvent<T> event);

}
