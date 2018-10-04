package com.apexmob.casus.location;

import com.apexmob.casus.SimpleObjectService;

import java.net.URI;

public class SimpleLayerService extends SimpleObjectService<Layer> implements LayerService {

    private final ZoneService zoneService;

    public SimpleLayerService(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @Override
    public Layer getObject(URI uri) {
        Layer retVal = super.getObject(uri);

        if (retVal!= null) {
            retVal.setZone(zoneService.getObject(retVal.getZoneUri()));
        }

        return retVal;
    }
}
