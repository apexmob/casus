package com.apexmob.casus;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Character represents the basic interface for player characters within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface Character extends GameObject {

//    Location getLocation();
//
//    void setLocation(Location location);

}
