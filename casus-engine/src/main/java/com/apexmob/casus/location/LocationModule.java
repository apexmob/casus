package com.apexmob.casus.location;

import com.apexmob.casus.BaseModule;
import com.apexmob.casus.GameEngine;

public class LocationModule extends BaseModule {

    private final ZoneService zoneService = new SimpleZoneService();
//    private final LocationStateService locationStateService = new InMemoryLocationStateService();
//    private final LocationItemStateService locationItemStateService = new InMemoryLocationItemStateService();
//    private final LocationMobStateService locationMobStateService = new InMemoryLocationMobStateService();
    private LocationService locationService;
    private LayerService layerService;

    @Override
    public void start(GameEngine gameEngine) {
        super.start(gameEngine);

        gameEngine.putService(ZoneService.class.getName(), zoneService);
//        gameEngine.putService(LocationItemStateService.class.getName(), locationItemStateService);
//        gameEngine.putService(LocationMobStateService.class.getName(), locationMobStateService);
//        gameEngine.putService(LocationStateService.class.getName(), locationStateService);

        layerService = new SimpleLayerService(zoneService);
        gameEngine.putService(LayerService.class.getName(), layerService);

        locationService = new SimpleLocationService(gameEngine, layerService);
        gameEngine.putService(LocationService.class.getName(), locationService);
    }

    @Override
    public void stop(GameEngine gameEngine) {
        gameEngine.removeService(LocationService.class.getName());
//        gameEngine.removeService(LocationStateService.class.getName());
//        gameEngine.removeService(LocationMobStateService.class.getName());
//        gameEngine.removeService(LocationItemStateService.class.getName());
//        gameEngine.removeService(ZoneService.class.getName());

        super.stop(gameEngine);
    }
}
