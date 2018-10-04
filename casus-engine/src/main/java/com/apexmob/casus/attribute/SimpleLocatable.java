package com.apexmob.casus.attribute;

import com.apexmob.casus.AbstractGameObjectAttribute;
import com.apexmob.casus.GameObject;
import com.apexmob.casus.location.Location;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleLocatable extends AbstractGameObjectAttribute<GameObject> implements Locatable {

    private Location location;

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
