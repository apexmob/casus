package com.apexmob.casus.character.location;

import com.apexmob.casus.BaseService;
import com.apexmob.casus.Character;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.attribute.CharacterHolder;
import com.apexmob.casus.attribute.Locatable;
import com.apexmob.casus.location.Location;

/**
 * SimpleCharacterLocationService is a simple concrete implementation of the {@link CharacterLocationService} interface.
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCharacterLocationService extends BaseService implements CharacterLocationService {

    private final GameEventDispatcher<CharacterLocationChangedEvent> locationChangedEventGameEventDispatcher;

    /**
     * Construct a new instance that dispatches events to the provided dispatcher.
     * @param locationChangedEventGameEventDispatcher The event dispatcher.
     */
    public SimpleCharacterLocationService(GameEventDispatcher<CharacterLocationChangedEvent> locationChangedEventGameEventDispatcher) {
        this.locationChangedEventGameEventDispatcher = locationChangedEventGameEventDispatcher;
    }

    /**
     * {@inheritDoc}
     */
    public void moveCharacter(Character character, Location newLocation) {
        Location oldLocation = getCharacterLocation(character);
        if (oldLocation != null) {
            getCharacterHolder(oldLocation).removeObject(character);
        }
        setCharacterLocation(character, newLocation);
        if (newLocation != null) {
            getCharacterHolder(newLocation).addObject(character);
        }
        locationChangedEventGameEventDispatcher.dispatch(new SimpleCharacterLocationChangedEvent(character, oldLocation, newLocation));
    }

    private void setCharacterLocation(Character character, Location location) {
        getLocatable(character).setLocation(location);
    }

    private Location getCharacterLocation(Character character) {
        return getLocatable(character).getLocation();
    }

    private Locatable getLocatable(Character character) {
        if (!character.supportsAttribute(Locatable.class)) {
            throw new IllegalStateException("The Character does not support the " + Locatable.class.getName()  + " Attribute");
        }
        return (Locatable) character.getAttribute(Locatable.class);
    }

    private CharacterHolder getCharacterHolder(Location location) {
        if (!location.supportsAttribute(CharacterHolder.class)) {
            throw new IllegalStateException("The Location does not support the " + CharacterHolder.class.getName()  + " Attribute");
        }
        return (CharacterHolder) location.getAttribute(CharacterHolder.class);
    }

}
