package com.apexmob.casus.net;

import java.net.URI;

/**
 * UriResolver is the interface used to abstract the means by which a component running in the game engine locates
 * an external resource.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface UriResolver {

    /**
     * Retrieve the URI.
     * @return The URI.
     */
    URI resolveUri();
}
