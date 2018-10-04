package com.apexmob.casus.command;

import com.apexmob.casus.AbstractGameObjectEvent;
import com.apexmob.casus.Character;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SimpleCommandEvent is a simple concrete implementation of the {@link CommandEvent} interface.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCommandEvent extends AbstractGameObjectEvent implements CommandEvent {

    private static final String ARGUMENT_REGEX = " ";

    private final Character character;
    private final String command;
    private final String[] arguements;

    /**
     * Construct a new instance.
     * @param character The character initiating the command.
     * @param command The command.
     */
    @JsonCreator
    public SimpleCommandEvent(@JsonProperty("character") Character character, @JsonProperty("command") String command) {
        super(character);
        this.character = character;
        this.command = command;
        arguements = command.split(ARGUMENT_REGEX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        return arguements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character getCharacter() {
        return character;
    }

    /**
     * {@inheritDoc}
     */
    @JsonGetter("command")
    @Override
    public String toString() {
        return command;
    }
}
