package com.apexmob.casus.action.move;

import com.apexmob.casus.Character;
import com.apexmob.casus.attribute.CharacterHolder;
import com.apexmob.casus.attribute.Locatable;
import com.apexmob.casus.character.location.CharacterLocationService;
import com.apexmob.casus.command.event.PostAliasingCommandEvent;
import com.apexmob.casus.location.Exit;
import com.apexmob.casus.location.Location;
import com.apexmob.casus.location.LocationService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.net.URI;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class MoveListenerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private URI newLocationUri = URI.create("test://test.com/test");

    private MoveListener listener;

    private LocationService locationService;
    private CharacterLocationService characterLocationService;
    private Character character;
    private PostAliasingCommandEvent event;
    private Location currentLocation;
    private Location newLocation;
    private CharacterHolder characterHolder;
    private Locatable locatable;
    private Exit exit;

    @Before
    public void before() {
        locationService = Mockito.mock(LocationService.class);
        characterLocationService = Mockito.mock(CharacterLocationService.class);
        character = Mockito.mock(Character.class);
        event = Mockito.mock(PostAliasingCommandEvent.class);
        currentLocation = Mockito.mock(Location.class);
        newLocation = Mockito.mock(Location.class);
        characterHolder = Mockito.mock(CharacterHolder.class);
        locatable = Mockito.mock(Locatable.class);
        exit = Mockito.mock(Exit.class);

        listener = new MoveListener(characterLocationService, locationService);
    }

    @Test
    public void testOnEvent_whenNonReadableCommand_thenDoNothing() {
        when(event.getArguments()).thenReturn(new String[]{"fail"});

        assertFalse(listener.onEvent(event));
    }

    @Test
    public void testOnEvent_whenReadableCommand_thenMoveCharacterAndReturnTrue() {
        when(character.getAttribute(Locatable.class)).thenReturn(locatable);
        when(character.supportsAttribute(Locatable.class)).thenReturn(true);
        when(locatable.getLocation()).thenReturn(currentLocation);

        when(currentLocation.findExit(eq("test"))).thenReturn(exit);
        when(exit.getDestinationUri()).thenReturn(newLocationUri);

        when(event.getArguments()).thenReturn(new String[]{"move", "test"});
        when(event.getCharacter()).thenReturn(character);

        when(locationService.getObject(eq(newLocationUri))).thenReturn(newLocation);

        assertTrue(listener.onEvent(event));
        verify(characterLocationService, times(1)).moveCharacter(same(character), same(newLocation));
    }

    @Test
    public void testOnEvent_whenReadableCommandButNoExit_thenNoCharacterChangeAndReturnFalse() {
        when(character.getAttribute(Locatable.class)).thenReturn(locatable);
        when(character.supportsAttribute(Locatable.class)).thenReturn(true);
        when(locatable.getLocation()).thenReturn(currentLocation);

        when(currentLocation.findExit(eq("test"))).thenReturn(null);
        when(exit.getDestinationUri()).thenReturn(newLocationUri);

        when(event.getArguments()).thenReturn(new String[]{"move", "test"});
        when(event.getCharacter()).thenReturn(character);

        when(locationService.getObject(eq(newLocationUri))).thenReturn(newLocation);

        assertFalse(listener.onEvent(event));
        verify(characterLocationService, times(0)).moveCharacter(same(character), same(newLocation));
    }

    @Test
    public void testOnEvent_whenReadableCommandButLocationNotFound_thenNoCharacterChangeAndReturnFalse() {
        when(character.getAttribute(Locatable.class)).thenReturn(locatable);
        when(character.supportsAttribute(Locatable.class)).thenReturn(true);
        when(locatable.getLocation()).thenReturn(currentLocation);

        when(currentLocation.findExit(eq("test"))).thenReturn(exit);
        when(exit.getDestinationUri()).thenReturn(newLocationUri);

        when(event.getArguments()).thenReturn(new String[]{"move", "test"});
        when(event.getCharacter()).thenReturn(character);

        when(locationService.getObject(eq(newLocationUri))).thenReturn(null);

        assertFalse(listener.onEvent(event));
        verify(characterLocationService, times(0)).moveCharacter(same(character), same(newLocation));
    }

    @Test
    public void testOnEvent_whenCharacterNotLocatable_thenThrowIllegalStateException() {
        thrown.expect(IllegalStateException.class);
        //TODO why does this result in NoSuchMethodError?
//        thrown.expectMessage("The Character does not support the " + Locatable.class.getName()  + " Attribute");

        when(event.getArguments()).thenReturn(new String[]{"move", "test"});
        when(event.getCharacter()).thenReturn(character);
        when(character.supportsAttribute(Locatable.class)).thenReturn(false);

        listener.onEvent(event);
    }

    private Set<Exit> buildSet(Exit exit) {
        Set<Exit> retVal = new LinkedHashSet<>();
        retVal.add(exit);

        return retVal;
    }
}
