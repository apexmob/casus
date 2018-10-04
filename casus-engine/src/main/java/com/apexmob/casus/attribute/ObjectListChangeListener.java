package com.apexmob.casus.attribute;

import com.apexmob.casus.attribute.event.ObjectAddedEvent;
import com.apexmob.casus.attribute.event.ObjectRemovedEvent;

public interface ObjectListChangeListener<T> {

    void onObjectAdded(ObjectAddedEvent<T> event);

    void onObjectRemoved(ObjectRemovedEvent<T> event);
}
