package com.apexmob.casus.command;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.SimpleGameEventDispatcher;
import com.apexmob.casus.command.event.PostAliasingCommandEvent;
import com.apexmob.casus.command.event.PreAliasingCommandEvent;
import com.apexmob.casus.command.event.UnrecognizedCommandEvent;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class CommandModule extends BaseModule {

    private GameEventDispatcher<PreAliasingCommandEvent> preAliasingCommandEventGameEventDispatcher;
    private GameEventDispatcher<PostAliasingCommandEvent> postAliasingCommandEventGameEventDispatcher;
    private GameEventDispatcher<UnrecognizedCommandEvent> unrecognizedCommandEventGameEventDispatcher;

    private CommandService commandService;

    @Override
    public void start(GameEngine game) {
        super.start(game);

        GameEventDispatcher<PreAliasingCommandEvent> preAlias = (GameEventDispatcher<PreAliasingCommandEvent>) game.getGameEventDispatcher(PreAliasingCommandEvent.class);
        if (preAlias == null) {
            preAliasingCommandEventGameEventDispatcher = new SimpleGameEventDispatcher<>();
            preAlias = preAliasingCommandEventGameEventDispatcher;
            game.putGameEventDispatcher(PreAliasingCommandEvent.class, preAliasingCommandEventGameEventDispatcher);
        }

        GameEventDispatcher<PostAliasingCommandEvent> postAlias = (GameEventDispatcher<PostAliasingCommandEvent>) game.getGameEventDispatcher(PostAliasingCommandEvent.class);
        if (postAlias == null) {
            postAliasingCommandEventGameEventDispatcher = new SimpleGameEventDispatcher<>();
            postAlias = postAliasingCommandEventGameEventDispatcher;
            game.putGameEventDispatcher(PostAliasingCommandEvent.class, postAliasingCommandEventGameEventDispatcher);
        }

        GameEventDispatcher<UnrecognizedCommandEvent> unrecognized = (GameEventDispatcher<UnrecognizedCommandEvent>) game.getGameEventDispatcher(UnrecognizedCommandEvent.class);
        if (unrecognized == null) {
            unrecognizedCommandEventGameEventDispatcher = new SimpleGameEventDispatcher<>();
            unrecognized = unrecognizedCommandEventGameEventDispatcher;
            game.putGameEventDispatcher(UnrecognizedCommandEvent.class, unrecognizedCommandEventGameEventDispatcher);
        }

        CommandService cmdService = (CommandService) game.getService(CommandService.class.getName());
        if (cmdService == null) {
            commandService = new SimpleCommandService(preAlias, postAlias, unrecognized);
            game.putService(CommandService.class.getName(), commandService);
        }

    }

    @Override
    public void stop(GameEngine game) {
        if (commandService != null) {
            game.removeService(CommandService.class.getName());
        }

        if (unrecognizedCommandEventGameEventDispatcher != null) {
            game.removeGameEventDispatcher(UnrecognizedCommandEvent.class);
        }

        if (preAliasingCommandEventGameEventDispatcher != null) {
            game.removeGameEventDispatcher(PreAliasingCommandEvent.class);
        }

        if (postAliasingCommandEventGameEventDispatcher != null) {
            game.removeGameEventDispatcher(PostAliasingCommandEvent.class);
        }

        super.stop(game);
    }
}
