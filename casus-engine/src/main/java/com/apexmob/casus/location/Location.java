package com.apexmob.casus.location;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameObject;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * Location is the basic representation of a room that can be visited by a {@link com.apexmob.casus.Character}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface Location extends GameObject {

    /**
     * Retrieve a unique identifier for the location.
     * @return a unique identifier for the location.
     */
    String getId();

    /**
     * Retrieve the URI for the layer that contains the location.
     * @return The URI.
     */
    URI getLayerUri();

    /**
     * Retrieve the exits for the location.
     * @return The exits for the location.
     */
    Set<Exit> getExits();

    /**
     * Retrieve the location's exit with the specified name.
     * @param name The name of the exit.
     * @return The Exit if present, otherwise null.
     */
    Exit findExit(String name);

    /**
     * Retrieve the layer that contains the location.
     * @return The layer containing the location.
     */
    Layer getLayer();

    /**
     * Set the layer for the location.
     * @param layer The layer.
     */
    void setLayer(Layer layer);

}
