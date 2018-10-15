package com.apexmob.casus.attribute;

import com.apexmob.casus.AbstractGameObjectAttribute;
import com.apexmob.casus.GameObject;
import com.apexmob.casus.location.Location;

/**
 * SimpleLocatable provides a simple implementation of {@link Locatable}.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleLocatable extends AbstractGameObjectAttribute<GameObject> implements Locatable {

    private Location location;

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
