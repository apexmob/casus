package com.apexmob.casus;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * GameObjectAttribute represents the basic interface for all attribute classes used to describe aspects of game objects. For
 * exmaple, items that can be held could have a "Holdable" attribute.  More complex attributes might have additional properties
 * or functionality.
 *
 * @author Chris Kirk
 * @since 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface GameObjectAttribute<T extends GameObject> {

    /**
     * Retrieve the game object to which the attribute applies.
     * @return The game object.
     */
    T getGameObject();

    /**
     * Set the game object to which the attribute applies.
     * @param gameObject The game object.
     */
    void setGameObject(T gameObject);
}
