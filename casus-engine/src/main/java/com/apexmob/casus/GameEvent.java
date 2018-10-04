package com.apexmob.casus;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * GameEvent represents the basic interface for all events within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface GameEvent {
}
