package com.apexmob.casus.client;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;

/**
 * ClientModule is an implementation of the {@link com.apexmob.casus.Module} interface that registers
 * functionality for a client-based game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class ClientModule extends BaseModule {

    private GameEventDistributor distributor;

    /**
     * {@inheritDoc}
     * <p>
     * <ul>
     * <li>Registers a {@link GameEventDistributor} implementation named as the interface class name.</li>
     * </ul>
     */
    @Override
    public void start(GameEngine game) {
        super.start(game);

        if (game.getService(GameEventDistributor.class.getName()) == null) {
            distributor = new SimpleGameEventDistributor();
            game.putService(GameEventDistributor.class.getName(), distributor);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine game) {
        if (distributor != null) {
            game.removeService(GameEventDistributor.class.getName());
        }

        super.stop(game);
    }
}
