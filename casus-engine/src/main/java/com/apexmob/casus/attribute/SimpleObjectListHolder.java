package com.apexmob.casus.attribute;

import com.apexmob.casus.AbstractGameObjectAttribute;
import com.apexmob.casus.attribute.event.ObjectListChangedEvent;
import com.apexmob.casus.attribute.event.SimpleObjectListChangedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimpleObjectListHolder provides a simple implementation of {@link ObjectListHolderAttribute}.
 *
 * @author Chris Kirk
 * @since 1.0
 * @param <T> The type of object held by the attribute.
 */
public class SimpleObjectListHolder<T> extends AbstractGameObjectAttribute implements ObjectListHolderAttribute<T> {

    private final List<T> objects = new ArrayList<>();
    private final List<ObjectListChangeListener> listeners = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeObject(T obj) {
        boolean retVal = objects.remove(obj);
        if (retVal) {
            for (ObjectListChangeListener listener : listeners) {
                listener.onChange(new SimpleObjectListChangedEvent(getGameObject(), obj, ObjectListChangedEvent.ChangeType.REMOVAL));
            }
        }

        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addObject(T obj) {
        boolean retVal = objects.add(obj);
        if (retVal) {
            for (ObjectListChangeListener listener : listeners) {
                listener.onChange(new SimpleObjectListChangedEvent(getGameObject(), obj, ObjectListChangedEvent.ChangeType.ADDITION));
            }
        }
        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObjectListChangeListener(ObjectListChangeListener<T> listener) {
        this.listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeObjectListChangeListener(ObjectListChangeListener<T> listener) {
        return listeners.remove(listener);
    }
}
