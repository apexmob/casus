package com.apexmob.casus.server;

import com.apexmob.casus.GameEngine;

import java.util.List;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class GameEngineConfiguration {

    private final GameEngine gameEngine;
    private final List<GameEngineConfigurer> configurers;

    public GameEngineConfiguration(GameEngine gameEngine, List<GameEngineConfigurer> configurers) {
        this.gameEngine = gameEngine;
        this.configurers = configurers;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public List<GameEngineConfigurer> getConfigurers() {
        return configurers;
    }
}
