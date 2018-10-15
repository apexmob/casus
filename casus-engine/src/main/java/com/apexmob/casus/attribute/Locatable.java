package com.apexmob.casus.attribute;

import com.apexmob.casus.GameObject;
import com.apexmob.casus.GameObjectAttribute;
import com.apexmob.casus.location.Location;

/**
 * Locatable represents the basic interface for attributes that hold a location for a game object.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface Locatable extends GameObjectAttribute<GameObject> {

    /**
     * Retrieve the game object's location.
     * @return The location of the object.
     */
    Location getLocation();

    /**
     * Set the location for the game object.
     * @param location The location.
     */
    void setLocation(Location location);
}
