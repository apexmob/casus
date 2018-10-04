package com.apexmob.casus.location;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;

/**
 * Exit is the basic representation of a method to move from one {@link Location} to another.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface Exit {

    /**
     * Retrieve the name of the exit.
     * @return The name of the exit.
     */
    String getName();

    /**
     * Retrieve the URI for the destination location.
     * @return The URI for the destination location.
     */
    URI getDestinationUri();
}
