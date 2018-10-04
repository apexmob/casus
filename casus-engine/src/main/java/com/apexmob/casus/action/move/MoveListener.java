package com.apexmob.casus.action.move;

import com.apexmob.casus.Character;
import com.apexmob.casus.attribute.Locatable;
import com.apexmob.casus.character.location.CharacterLocationService;
import com.apexmob.casus.command.event.PostAliasingCommandEvent;
import com.apexmob.casus.command.listener.FilterByFirstArgumentCommandListener;
import com.apexmob.casus.location.Exit;
import com.apexmob.casus.location.Location;
import com.apexmob.casus.location.LocationService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MoveListener is a {@link com.apexmob.casus.GameEventListener} that listens for {@link PostAliasingCommandEvent} events
 * that contain move commands.  Upon receiving a move event, it attempts to move the character in the provided direction.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class MoveListener extends FilterByFirstArgumentCommandListener<PostAliasingCommandEvent> {

    private static final List<String> PROCESSABLE_COMMANDS = Collections.unmodifiableList(Arrays.asList(new String[]{"move"}));

    private final LocationService locationService;
    private final CharacterLocationService characterLocationService;

    /**
     * Construct a new instance.
     * @param characterLocationService The character location service that orchestrates character moves.
     * @param locationService The location service containing {@link Location} instances.
     */
    public MoveListener(CharacterLocationService characterLocationService, LocationService locationService) {
        this.characterLocationService = characterLocationService;
        this.locationService = locationService;
    }

    /**
     * Retrieve the list of commands that the listener can understand.
     * @return The list of commands.
     */
    @Override
    protected List<String> getProcessableFirstArguments() {
        return PROCESSABLE_COMMANDS;
    }

    /**
     * Perform the necessary logic on the event when a processable command is received.
     * @param commandEvent The event received.
     * @return True if the event processed successfully, otherwise false.
     */
    @Override
    protected boolean doExecute(PostAliasingCommandEvent commandEvent) {
        boolean retVal = false;
        if (commandEvent.getArguments().length > 1) {

            Exit exit = getLocatable(commandEvent.getCharacter()).getLocation().findExit(commandEvent.getArguments()[1]);

            if (exit != null) {
                Location location = locationService.getObject(exit.getDestinationUri());
                if (location != null) {
                    characterLocationService.moveCharacter(commandEvent.getCharacter(), location);
                    retVal = true;
                }
            }
        }
        return retVal;
    }

    private Locatable getLocatable(Character character) {
        if (!character.supportsAttribute(Locatable.class)) {
            throw new IllegalStateException("The Character does not support the " + Locatable.class.getName()  + " Attribute");
        }
        return (Locatable) character.getAttribute(Locatable.class);
    }

}
