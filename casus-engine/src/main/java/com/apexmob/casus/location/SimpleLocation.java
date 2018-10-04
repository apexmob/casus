package com.apexmob.casus.location;

import com.apexmob.casus.AbstractGameObject;

import java.net.URI;
import java.util.Set;

public class SimpleLocation extends AbstractGameObject implements Location {

    private String name;
    private String description;
    private String id;
    private Set<Exit> exits;
    private URI layerUri;
    private Layer layer;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Set<Exit> getExits() {
        return exits;
    }

    public void setExits(Set<Exit> exits) {
        this.exits = exits;
    }

    @Override
    public URI getLayerUri() {
        return layerUri;
    }

    public void setLayerUri(URI layerUri) {
        this.layerUri = layerUri;
    }

    @Override
    public Exit findExit(String name) {
        Exit retVal = null;

        for (Exit exit : getExits()) {
            if (name.equals(exit.getName())) {
                retVal = exit;
                break;
            }
        }

        return retVal;
    }

    @Override
    public Layer getLayer() {
        return layer;
    }

    @Override
    public void setLayer(Layer layer) {
        this.layer = layer;
    }

}
