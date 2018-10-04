package com.apexmob.casus.action;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.Module;
import com.apexmob.casus.action.move.MoveActionModule;
import com.apexmob.casus.character.location.CharacterLocationChangedEvent;
import com.apexmob.casus.character.location.CharacterLocationService;

/**
 * ActionModule is an implementation of the {@link com.apexmob.casus.Module} interface that registers
 * functionality for all actions.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class ActionModule extends BaseModule {

    private static final Module[] ACTION_MODULES = new Module[]{
            new MoveActionModule()
//            new LookActionModule(),
//            new GetActionModule(),
//            new DropActionModule(),
//            new InventoryActionModule(),
//            new KillActionModule()
    };

    /**
     * {@inheritDoc}
     * <p>
     * <ul>
     * <li>Delegates registration to {@link MoveActionModule}.</li>
     * </ul>
     */
    @Override
    public void start(GameEngine gameEngine) {
        super.start(gameEngine);

        for (int i = 0; i < ACTION_MODULES.length; i++) {
            ACTION_MODULES[i].start(gameEngine);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine gameEngine) {
        for (int i = 0; i < ACTION_MODULES.length; i++) {
            ACTION_MODULES[ACTION_MODULES.length-1 - i].stop(gameEngine);
        }

        super.stop(gameEngine);
    }
}
