package com.apexmob.casus.command;

import com.apexmob.casus.Character;
import com.apexmob.casus.GameObjectEvent;

/**
 * CommandEvent is the basic interface used to define commands issued from a player/character to the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface CommandEvent extends GameObjectEvent {
    /**
     * Retrieve the arguments for the command.
     * @return The command arguments.
     */
    String[] getArguments();

    /**
     * Retrieve the character that issued the command.
     * @return The character that issued the command.
     */
    Character getCharacter();
}
