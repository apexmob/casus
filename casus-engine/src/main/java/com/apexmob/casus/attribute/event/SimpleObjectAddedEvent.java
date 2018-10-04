package com.apexmob.casus.attribute.event;

import com.apexmob.casus.AbstractGameObjectEvent;
import com.apexmob.casus.GameObject;

public class SimpleObjectAddedEvent<T> extends AbstractGameObjectEvent implements ObjectAddedEvent<T>{

    private final T obj;

    public SimpleObjectAddedEvent(GameObject gameObject, T obj) {
        super(gameObject);
        this.obj = obj;
    }

    @Override
    public T getObject() {
        return obj;
    }
}
