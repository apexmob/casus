package com.apexmob.casus.attribute;

import com.apexmob.casus.GameObjectAttribute;

import java.util.List;

/**
 * ObjectListHolderAttribute represents the basic interface for attributes that hold a list of objects.
 *
 * @author Chris Kirk
 * @since 1.0
 * @param <T> The type of object held by the attribute.
 */
public interface ObjectListHolderAttribute<T> extends GameObjectAttribute {

    /**
     * Retrieve the list of objects held within the attribute.
     * @return The list of objects.
     */
    List<T> getObjects();

    /**
     * Remove an object from the list.
     * @param obj The object to remove.
     * @return True if the object was present and removed, otherwise false.
     */
    boolean removeObject(T obj);

    /**
     * Add an object to the list.
     * @param obj The object to add.
     * @return True if the object was added, otherwise false.
     */
    boolean addObject(T obj);

    /**
     * Add an event listener.
     * @param listener The listener to add.
     */
    void addObjectListChangeListener(ObjectListChangeListener<T> listener);

    /**
     * Remove an event listener.
     * @param listener The listener to remove.
     * @return True if the listener was present and removed, otherwise false.
     */
    boolean removeObjectListChangeListener(ObjectListChangeListener<T> listener);
}
