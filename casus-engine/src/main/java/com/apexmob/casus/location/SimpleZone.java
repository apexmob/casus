package com.apexmob.casus.location;

import java.util.List;

public class SimpleZone implements Zone {

    private String name;
    private List<SimpleLayer> layers;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLayers(List<SimpleLayer> layers) {
        this.layers = layers;
        for (SimpleLayer layer : layers) {
            layer.setZone(this);
        }
    }

    @Override
    public SimpleLayer findLayerById(String id) {
        SimpleLayer retVal = null;

        for (SimpleLayer layer : layers) {
            if (id.equals(layer.getId())) {
                retVal = layer;
                break;
            }
        }

        return retVal;
    }
}
