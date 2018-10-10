package com.apexmob.casus.attribute;

import com.apexmob.casus.attribute.event.ObjectListChangedEvent;

public interface ObjectListChangeListener<T> {

    void onChange(ObjectListChangedEvent<T> event);

}
