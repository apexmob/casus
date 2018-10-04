package com.apexmob.casus.action.move;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.character.location.CharacterLocationService;
import com.apexmob.casus.command.CommandService;
import com.apexmob.casus.command.event.PostAliasingCommandEvent;
import com.apexmob.casus.location.LocationService;

/**
 * MoveActionModule is an implementation of the {@link com.apexmob.casus.Module} interface that registers
 * functionality for the "move" actions.
 * <p>
 * Proper functionality is dependent on the following:
 * <ul>
 * <li>Presense of a {@link CommandService} service</li>
 * <li>Presense of a {@link LocationService} service</li>
 * <li>Presense of a {@link CharacterLocationService} service</li>
 * <li>Presense of a {@link GameEventDispatcher} dispatching {@link PostAliasingCommandEvent} events</li>
 * </ul>
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class MoveActionModule extends BaseModule {

    private MoveAliasor moveAliasor = null;
    private MoveListener moveListener = null;

    /**
     * {@inheritDoc}
     * <p>
     * <ul>
     * <li>Registers a {@link com.apexmob.casus.command.CommandAliasor} with the {@link CommandService}.</li>
     * <li>Registers a {@link MoveListener} to listen for {@link PostAliasingCommandEvent} events and initiate movement.</li>
     * </ul>
     */
    @Override
    public void start(GameEngine gameEngine) {
        super.start(gameEngine);

        CommandService commandService = (CommandService) gameEngine.getService(CommandService.class.getName());
        if (commandService != null) {
            moveAliasor = new MoveAliasor();
            commandService.addCommandAliasor(moveAliasor);
        }

        LocationService locationService = (LocationService) gameEngine.getService(LocationService.class.getName());
        CharacterLocationService characterLocationService = (CharacterLocationService) gameEngine.getService(CharacterLocationService.class.getName());
        GameEventDispatcher<PostAliasingCommandEvent> postAliasingCommandEventGameEventDispatcher = (GameEventDispatcher<PostAliasingCommandEvent>) gameEngine.getGameEventDispatcher(PostAliasingCommandEvent.class);

        if (postAliasingCommandEventGameEventDispatcher != null && characterLocationService != null && locationService != null) {
            moveListener = new MoveListener(characterLocationService, locationService);
            postAliasingCommandEventGameEventDispatcher.addListener(moveListener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine gameEngine) {

        GameEventDispatcher<PostAliasingCommandEvent> postAliasingCommandEventGameEventDispatcher = (GameEventDispatcher<PostAliasingCommandEvent>) gameEngine.getGameEventDispatcher(PostAliasingCommandEvent.class);
        if (moveListener != null) {
            postAliasingCommandEventGameEventDispatcher.removeListener(moveListener);
        }

        CommandService commandService = (CommandService) gameEngine.getService(CommandService.class.getName());
        if (moveAliasor != null) {
            commandService.removeCommandAliasor(moveAliasor);
        }

        super.stop(gameEngine);
    }
}
