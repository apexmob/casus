package com.apexmob.casus.client.stomp.authn;

import com.apexmob.casus.*;
import com.apexmob.casus.client.GameEventDistributor;
import com.apexmob.casus.client.UsernamePasswordAuthenticationService;
import com.apexmob.casus.client.stomp.StompConfigurationService;
import com.apexmob.casus.command.CommandEvent;

/**
 * StompUsernamePasswordAuthenticationModule is an implementation of the {@link com.apexmob.casus.Module} interface that registers
 * authentication functionality for a stomp web service within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class StompUsernamePasswordAuthenticationModule extends BaseModule {

    private StompUsernamePasswordAuthenticationService usernamePasswordAuthenticationService = null;

    /**
     * {@inheritDoc}
     * <p>
     * <ul>
     * <li>Registers a {@link UsernamePasswordAuthenticationService} implemenation named as the interface class name.</li>
     * </ul>
     */
    @Override
    public void start(GameEngine game) {
        super.start(game);

        StompConfigurationService configurationService = (StompConfigurationService) game.getService(StompConfigurationService.class.getName());
        GameEventDistributor distributor = (GameEventDistributor) game.getService(GameEventDistributor.class.getName());
        GameEventDispatcher<CommandEvent> manager = (GameEventDispatcher<CommandEvent>) game.getGameEventDispatcher(CommandEvent.class);

        if (configurationService != null && distributor != null && manager != null) {
            usernamePasswordAuthenticationService = new StompUsernamePasswordAuthenticationService(
                    configurationService,
                    distributor,
                    manager
            );
            game.putService(UsernamePasswordAuthenticationService.class.getName(), usernamePasswordAuthenticationService);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine game) {

        if (usernamePasswordAuthenticationService != null) {
            game.removeService(UsernamePasswordAuthenticationService.class.getName());
        }

        super.stop(game);
    }
}
