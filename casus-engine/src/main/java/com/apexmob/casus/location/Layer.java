package com.apexmob.casus.location;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;

/**
 * Layer is the basic representation of a logical group of one or more {@link Location}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface Layer {

    /**
     * Retrieve the name of the layer.
     * @return The name of the layer.
     */
    String getName();

    /**
     * Retrieve a unique identifier for the layer.
     * @return a unique identifier for the layer.
     */
    String getId();

    /**
     * Retrieve the zone that contains the layer.
     * @return The zone that contains the layer.
     */
    Zone getZone();

    /**
     * Set the zone that contains the layer.
     * @param zone The zone that contains the layer.
     */
    void setZone(Zone zone);

    /**
     * Retrieve the URI for the zone that contains the layer.
     * @return The URI.
     */
    URI getZoneUri();

    /**
     * Retrieve the location, from the layer, with the provided ID.
     * @param id The ID of the location.
     * @return The location if found, otherwise null.
     */
    Location findLocationById(String id);
}
