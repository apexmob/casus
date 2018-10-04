package com.apexmob.casus.character.location;

import com.apexmob.casus.AbstractGameObjectEvent;
import com.apexmob.casus.Character;
import com.apexmob.casus.location.Location;

/**
 * SimpleCharacterLocationChangedEvent is a simple, concrete implementation of the {@link CharacterLocationChangedEvent} interface.
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCharacterLocationChangedEvent extends AbstractGameObjectEvent<Character> implements CharacterLocationChangedEvent {

    private final Location oldLocation;
    private final Location newLocation;

    /**
     * Construct a new instance.
     * @param character The character whose location has changed.
     * @param oldLocation The location of the character before the change.
     * @param newLocation The location of the character after the change.
     */
    public SimpleCharacterLocationChangedEvent(Character character, Location oldLocation, Location newLocation) {
        super(character);

        this.oldLocation = oldLocation;
        this.newLocation = newLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getOldLocation() {
        return oldLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getNewLocation() {
        return newLocation;
    }

}
