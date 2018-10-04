package com.apexmob.casus.location;

import com.apexmob.casus.SimpleObjectService;
import com.apexmob.casus.GameEngine;

import java.net.URI;

public class SimpleLocationService extends SimpleObjectService<Location> implements LocationService {

    private final LayerService layerService;
    private final GameEngine game;

    public SimpleLocationService(GameEngine game, LayerService layerService) {
        this.game = game;
        this.layerService = layerService;
    }

    @Override
    public Location getObject(URI uri) {
        Location retVal = super.getObject(uri);

        if (retVal!= null) {
            retVal.setLayer(layerService.getObject(retVal.getLayerUri()));
            retVal.start(game);
        }

        return retVal;
    }
}
