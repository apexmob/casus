package com.apexmob.casus.client.command;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.SimpleGameEventDispatcher;
import com.apexmob.casus.command.CommandEvent;

//TODO docs
//TODO tests
/**
 * @author Chris Kirk
 * @since 1.0
 */
public class CommandModule extends BaseModule {

    private GameEventDispatcher<CommandEvent> eventDispatcher;

    @Override
    public void start(GameEngine game) {
        super.start(game);

        GameEventDispatcher<CommandEvent> dispatcher = (GameEventDispatcher<CommandEvent>) game.getGameEventDispatcher(CommandEvent.class);
        if (dispatcher == null) {
            eventDispatcher = new SimpleGameEventDispatcher<>();
            game.putGameEventDispatcher(CommandEvent.class, eventDispatcher);
        }
    }

    @Override
    public void stop(GameEngine game) {

        if (eventDispatcher != null) {
            game.removeGameEventDispatcher(CommandEvent.class);
        }

        super.stop(game);
    }
}
