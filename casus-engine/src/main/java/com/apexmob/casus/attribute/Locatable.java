package com.apexmob.casus.attribute;

import com.apexmob.casus.GameObject;
import com.apexmob.casus.GameObjectAttribute;
import com.apexmob.casus.location.Location;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public interface Locatable extends GameObjectAttribute<GameObject> {

    Location getLocation();

    void setLocation(Location location);
}
