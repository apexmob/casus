package com.apexmob.casus.character.location;

import com.apexmob.casus.Character;
import com.apexmob.casus.location.Location;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertSame;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCharacterLocationChangedEventTest {

    @Test
    public void testConstructor_whenCreated_thenAllReadable() {
        Character character = Mockito.mock(Character.class);
        Location oldLocation = Mockito.mock(Location.class);
        Location newLocation = Mockito.mock(Location.class);

        SimpleCharacterLocationChangedEvent event = new SimpleCharacterLocationChangedEvent(character, oldLocation, newLocation);

        assertSame(character, event.getGameObject());
        assertSame(oldLocation, event.getOldLocation());
        assertSame(newLocation, event.getNewLocation());
    }
}
