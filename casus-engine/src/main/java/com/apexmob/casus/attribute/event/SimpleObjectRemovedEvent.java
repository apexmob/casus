package com.apexmob.casus.attribute.event;

import com.apexmob.casus.AbstractGameObjectEvent;
import com.apexmob.casus.GameObject;

public class SimpleObjectRemovedEvent<T> extends AbstractGameObjectEvent implements ObjectRemovedEvent<T>{

    private final T obj;

    public SimpleObjectRemovedEvent(GameObject gameObject, T obj) {
        super(gameObject);
        this.obj = obj;
    }

    @Override
    public T getObject() {
        return obj;
    }
}
