package com.apexmob.casus.character.location;

import com.apexmob.casus.Character;
import com.apexmob.casus.GameObjectEvent;
import com.apexmob.casus.location.Location;

/**
 * CharacterLocationChangedEvent is an event that represents a change in a character's location.
 * @author Chris Kirk
 * @since 1.0
 */
public interface CharacterLocationChangedEvent extends GameObjectEvent<Character> {

    /**
     * Retrieve the location of the character before the change.
     * @return The location of the character before the change.
     */
    Location getOldLocation();

    /**
     * Retrieve the location of the character after the change.
     * @return The location of the character after the change.
     */
    Location getNewLocation();

}
