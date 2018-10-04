package com.apexmob.casus.attribute.event;

import com.apexmob.casus.GameEvent;

public interface ObjectRemovedEvent<T> extends GameEvent {

    T getObject();
}
