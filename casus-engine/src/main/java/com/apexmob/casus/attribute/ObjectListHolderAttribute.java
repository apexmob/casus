package com.apexmob.casus.attribute;

import com.apexmob.casus.GameObjectAttribute;

import java.util.List;

public interface ObjectListHolderAttribute<T> extends GameObjectAttribute {

    List<T> getObjects();

    boolean removeObject(T obj);

    boolean addObject(T obj);

    void addObjectListChangeListener(ObjectListChangeListener<T> listener);

    boolean removeObjectListChangeListener(ObjectListChangeListener<T> listener);
}
