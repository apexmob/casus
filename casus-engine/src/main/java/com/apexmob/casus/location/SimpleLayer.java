package com.apexmob.casus.location;

import java.net.URI;
import java.util.List;

public class SimpleLayer implements Layer {

    private String name;
    private List<Location> locations;
    private String id;
    private Zone zone;
    private URI zoneUri;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
        for (Location location : locations) {
            location.setLayer(this);
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Zone getZone() {
        return zone;
    }

    @Override
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public URI getZoneUri() {
        return zoneUri;
    }

    public void setZoneUri(URI zoneUri) {
        this.zoneUri = zoneUri;
    }

    @Override
    public Location findLocationById(String id) {
        Location retVal = null;

        for (Location location : locations) {
            if (id.equals(location.getId())) {
                retVal = location;
                break;
            }
        }

        return retVal;
    }
}
