package com.apexmob.casus.attribute.event;

import com.apexmob.casus.GameEvent;

public interface ObjectListChangedEvent<T> extends GameEvent {

    T getObject();

    ChangeType getChangeType();

    static enum ChangeType {
        ADDITION,
        REMOVAL
    }
}
