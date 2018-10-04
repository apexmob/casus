package com.apexmob.casus.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class SimpleExit implements Exit {

    private final String name;
    private final URI destinationUri;

    @JsonCreator
    public SimpleExit(@JsonProperty("name") String name, @JsonProperty("") URI destinationUri) {
        this.name = name;
        this.destinationUri = destinationUri;
    }

    public String getName() {
        return name;
    }

    public URI getDestinationUri() {
        return destinationUri;
    }
}
