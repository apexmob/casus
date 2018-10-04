package com.apexmob.casus.client.stomp;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;

/**
 * StompClientModule is an implementation of the {@link com.apexmob.casus.Module} interface that registers
 * functionality for a stomp web service within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class StompClientModule extends BaseModule {

    private StompConfigurationService configurationService = null;

    /**
     * {@inheritDoc}
     * <p>
     * <ul>
     * <li>Registers a {@link StompConfigurationService} implementation named as the interface class name.</li>
     * </ul>
     */
    @Override
    public void start(GameEngine game) {
        super.start(game);

        StompConfigurationService configService = (StompConfigurationService) game.getService(StompConfigurationService.class.getName());
        if (configService == null) {
            this.configurationService = new SimpleStompConfigurationService();
            game.putService(StompConfigurationService.class.getName(), this.configurationService);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine game) {

        if (configurationService != null) {
            game.removeService(StompConfigurationService.class.getName());
        }

        super.stop(game);
    }
}
