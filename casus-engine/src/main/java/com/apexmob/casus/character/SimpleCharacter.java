package com.apexmob.casus.character;

import com.apexmob.casus.AbstractGameObject;
import com.apexmob.casus.Character;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple implementation of the {@link Character} interface.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCharacter extends AbstractGameObject implements Character {

    /**
     * Construct a new instance.
     */
    public SimpleCharacter() {
        super();
    }

    /**
     * Construct a new instance.
     * @param name The name of the character.
     */
    @JsonCreator
    public SimpleCharacter(@JsonProperty("name") String name) {
        this();
        setName(name);
    }
}
