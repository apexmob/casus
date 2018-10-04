package com.apexmob.casus.character.location;

import com.apexmob.casus.Character;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.attribute.CharacterHolder;
import com.apexmob.casus.attribute.Locatable;
import com.apexmob.casus.location.Location;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCharacterLocationServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private GameEventDispatcher<CharacterLocationChangedEvent> dispatcher;
    private SimpleCharacterLocationService service;
    private Location oldLocation;
    private Location newLocation;
    private Character character;
    private Locatable locatable;
    private CharacterHolder oldCharacterHolder;
    private CharacterHolder newCharacterHolder;

    @Before
    public void before() {
        dispatcher = Mockito.mock(GameEventDispatcher.class);
        service = new SimpleCharacterLocationService(dispatcher);

        oldLocation = Mockito.mock(Location.class);
        newLocation = Mockito.mock(Location.class);
        character = Mockito.mock(Character.class);
        locatable = Mockito.mock(Locatable.class);
        oldCharacterHolder = Mockito.mock(CharacterHolder.class);
        newCharacterHolder = Mockito.mock(CharacterHolder.class);

        when(character.supportsAttribute(Locatable.class)).thenReturn(true);
        when(character.getAttribute(Locatable.class)).thenReturn(locatable);
        when(locatable.getLocation()).thenReturn(oldLocation);

        when(oldLocation.getAttribute(CharacterHolder.class)).thenReturn(oldCharacterHolder);
        when(oldLocation.supportsAttribute(CharacterHolder.class)).thenReturn(true);
        when(newLocation.getAttribute(CharacterHolder.class)).thenReturn(newCharacterHolder);
        when(newLocation.supportsAttribute(CharacterHolder.class)).thenReturn(true);
    }

    @Test
    public void testMoveCharacter_whenCalled_thenCharacterLocationChanged() {
        service.moveCharacter(character, newLocation);

        verify(locatable, times(1)).setLocation(same(newLocation));
    }

    @Test
    public void testMoveCharacter_whenCalled_thenCharacterAddedToNewLocation() {
        service.moveCharacter(character, newLocation);

        verify(newCharacterHolder, times(1)).addObject(same(character));
    }

    @Test
    public void testMoveCharacter_whenCalled_thenCharacterRemovedFromOldLocation() {
        service.moveCharacter(character, newLocation);

        verify(oldCharacterHolder, times(1)).removeObject(same(character));
    }

    @Test
    public void testMoveCharacter_whenCalled_thenCharacterLocationChangedEventDispatched() {
        service.moveCharacter(character, newLocation);

        ArgumentCaptor<CharacterLocationChangedEvent> eventArgumentCaptor = ArgumentCaptor.forClass(CharacterLocationChangedEvent.class);
        verify(dispatcher, times(1)).dispatch(eventArgumentCaptor.capture());

        assertSame(character, eventArgumentCaptor.getValue().getGameObject());
        assertSame(oldLocation, eventArgumentCaptor.getValue().getOldLocation());
        assertSame(newLocation, eventArgumentCaptor.getValue().getNewLocation());
    }

    @Test
    public void testMoveCharacter_whenCharacterIsNotLocatable_thenThrowIllegalStateException() {
        thrown.expect(IllegalStateException.class);
        //TODO why does this result in NoSuchMethodError?
//        thrown.expectMessage("The Character does not support the " + Locatable.class.getName()  + " Attribute");

        when(character.supportsAttribute(Locatable.class)).thenReturn(false);

        service.moveCharacter(character, newLocation);
    }

    @Test
    public void testMoveCharacter_whenNewLocationIsNotCharacterHolder_thenThrowIllegalStateException() {
        thrown.expect(IllegalStateException.class);
        //TODO why does this result in NoSuchMethodError?
//        thrown.expectMessage("The Location does not support the " + CharacterHolder.class.getName()  + " Attribute");

        when(newLocation.supportsAttribute(CharacterHolder.class)).thenReturn(false);

        service.moveCharacter(character, newLocation);
    }
}
