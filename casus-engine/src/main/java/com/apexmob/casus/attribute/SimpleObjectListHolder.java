package com.apexmob.casus.attribute;

import com.apexmob.casus.AbstractGameObjectAttribute;
import com.apexmob.casus.attribute.event.SimpleObjectAddedEvent;
import com.apexmob.casus.attribute.event.SimpleObjectRemovedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleObjectListHolder<T> extends AbstractGameObjectAttribute implements ObjectListHolderAttribute<T> {

    private final List<T> objects = new ArrayList<>();
    private final List<ObjectListChangeListener> listeners = new ArrayList<>();

    @Override
    public List<T> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    @Override
    public boolean removeObject(T obj) {
        boolean retVal = objects.remove(obj);
        if (retVal) {
            for (ObjectListChangeListener listener : listeners) {
                listener.onObjectRemoved(new SimpleObjectRemovedEvent(getGameObject(), obj));
            }
        }

        return retVal;
    }

    @Override
    public boolean addObject(T obj) {
        boolean retVal = objects.add(obj);
        if (retVal) {
            for (ObjectListChangeListener listener : listeners) {
                listener.onObjectAdded(new SimpleObjectAddedEvent(getGameObject(), obj));
            }
        }
        return retVal;
    }

    @Override
    public void addObjectListChangeListener(ObjectListChangeListener<T> listener) {
        this.listeners.add(listener);
    }

    @Override
    public boolean removeObjectListChangeListener(ObjectListChangeListener<T> listener) {
        return listeners.remove(listener);
    }
}
