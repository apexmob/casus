package com.apexmob.casus.attribute.event;

import com.apexmob.casus.GameEvent;

public interface ObjectAddedEvent<T> extends GameEvent {

    T getObject();
}
