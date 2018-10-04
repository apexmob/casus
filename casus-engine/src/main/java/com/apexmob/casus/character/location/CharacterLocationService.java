package com.apexmob.casus.character.location;

import com.apexmob.casus.Character;
import com.apexmob.casus.Service;
import com.apexmob.casus.location.Location;

/**
 * CharacterLocationService is the basic representation of a service that manages the movement of
 * a {@link Character}.
 * @author Chris Kirk
 * @since 1.0
 */
public interface CharacterLocationService extends Service {

    /**
     * Move the Character to the specified location.
     * @param character The character to move.
     * @param newLocation The new location for the character.
     */
    void moveCharacter(Character character, Location newLocation);
}
