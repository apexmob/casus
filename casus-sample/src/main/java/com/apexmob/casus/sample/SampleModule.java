package com.apexmob.casus.sample;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.command.CommandEvent;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SampleModule extends BaseModule {

    private SampleCommandEventListener commandEventListener;

    @Override
    public void start(GameEngine game) {
        super.start(game);

        SampleEventDispatcher sampleEventDispatcher = new SampleEventDispatcher();
        game.putGameEventDispatcher(SampleEvent.class, sampleEventDispatcher);

        GameEventDispatcher<CommandEvent> commandEventDispatcher = (GameEventDispatcher<CommandEvent>) game.getGameEventDispatcher(CommandEvent.class);
        if (commandEventDispatcher != null) {
            commandEventListener = new SampleCommandEventListener(sampleEventDispatcher);
            commandEventDispatcher.addListener(commandEventListener);
        }
    }

    @Override
    public void stop(GameEngine game) {
        super.stop(game);
    }
}
