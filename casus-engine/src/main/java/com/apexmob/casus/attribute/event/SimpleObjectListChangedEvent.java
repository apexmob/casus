package com.apexmob.casus.attribute.event;

import com.apexmob.casus.AbstractGameObjectEvent;
import com.apexmob.casus.GameObject;

public class SimpleObjectListChangedEvent<T> extends AbstractGameObjectEvent implements ObjectListChangedEvent<T> {

    private final T obj;
    private final ChangeType changeType;

    public SimpleObjectListChangedEvent(GameObject gameObject, T obj, ChangeType changeType) {
        super(gameObject);
        this.obj = obj;
        this.changeType = changeType;
    }

    @Override
    public T getObject() {
        return obj;
    }

    @Override
    public ChangeType getChangeType() {
        return changeType;
    }
}
