package com.apexmob.casus.command.event;

import com.apexmob.casus.Character;
import com.apexmob.casus.command.SimpleCommandEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimplePostAliasingCommandEvent extends SimpleCommandEvent implements PostAliasingCommandEvent {
    @JsonCreator
    public SimplePostAliasingCommandEvent(@JsonProperty("character") Character character, @JsonProperty("command") String command) {
        super(character, command);
    }
}
