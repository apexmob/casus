package com.apexmob.casus.character.location;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.SimpleGameEventDispatcher;

/**
 * CharacterLocationModule is an implementation of the {@link com.apexmob.casus.Module} interface that registers
 * functionality for Character Locations within the game engine.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class CharacterLocationModule extends BaseModule {

    private GameEventDispatcher<CharacterLocationChangedEvent> locationChangedDispatcher;
    private CharacterLocationService characterLocationService;

    /**
     * {@inheritDoc}
     * <p>
     * <ul>
     * <li>Registers a {@link GameEventDispatcher} implemenation for dispatching {@link CharacterLocationChangedEvent} events and named by event class name.</li>
     * <li>Registers a {@link CharacterLocationService} implemenation named as the interface class name.</li>
     * </ul>
     */
    @Override
    public void start(GameEngine gameEngine) {
        super.start(gameEngine);

        GameEventDispatcher<CharacterLocationChangedEvent> tempLocationChangedDispatcher = gameEngine.getGameEventDispatcher(CharacterLocationChangedEvent.class);
        if (tempLocationChangedDispatcher == null) {
            locationChangedDispatcher = new SimpleGameEventDispatcher<>();
            tempLocationChangedDispatcher = locationChangedDispatcher;
            gameEngine.putGameEventDispatcher(CharacterLocationChangedEvent.class, locationChangedDispatcher);
        }

        CharacterLocationService charLocationService = (CharacterLocationService) gameEngine.getService(CharacterLocationService.class.getName());
        if (charLocationService == null) {
            characterLocationService = new SimpleCharacterLocationService(tempLocationChangedDispatcher);
            gameEngine.putService(CharacterLocationService.class.getName(), characterLocationService);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameEngine gameEngine) {

        if (characterLocationService != null) {
            gameEngine.removeService(CharacterLocationService.class.getName());
        }

        if (locationChangedDispatcher != null) {
            gameEngine.removeGameEventDispatcher(CharacterLocationChangedEvent.class);
        }

        super.stop(gameEngine);
    }
}
