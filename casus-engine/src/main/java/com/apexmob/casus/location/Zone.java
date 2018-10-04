package com.apexmob.casus.location;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Zone is the basic representation of an area that can be explored by a {@link com.apexmob.casus.Character}.  A zone
 * is made up of one or more {@link Layer}.  A {@link Layer} is made of one or more {@link Location}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface Zone {
    /**
     * Retrieve the name of the zone.
     * @return The name of the zone.
     */
    String getName();

    /**
     * Retrieve the layer, from the zone, with the provided ID.
     * @param id The ID of the layer.
     * @return The layer if found, otherwise null.
     */
    Layer findLayerById(String id);
}
