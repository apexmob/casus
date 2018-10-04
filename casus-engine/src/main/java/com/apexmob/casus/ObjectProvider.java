package com.apexmob.casus;

import java.net.URI;

/**
 * ObjectProvider is basic representation of a implementation that provides read access to externally stored objects.
 * @param <T> The type of object that may be retrieved.
 */
public interface ObjectProvider<T> {

    /**
     * Retrieve the object with the specified URI.
     * @param uri The URI of the object.
     * @return The object if found, otherwise null.
     */
    T getObject(URI uri);
}
