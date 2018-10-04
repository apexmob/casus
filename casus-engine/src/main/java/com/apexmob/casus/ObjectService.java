package com.apexmob.casus;

import java.net.URI;

/**
 * ObjectService is the basic representation of a service that provides read access to an object stored externally.
 * The service locates the object from one of the {@link ObjectProvider}.
 * @param <T> The type of object that may be retrieved.
 */
public interface ObjectService<T> extends Service {

    /**
     * Retrieve the object with the specified URI.
     * @param uri The URI of the object.
     * @return The object if found, otherwise null.
     */
    T getObject(URI uri);

    /**
     * Add an ObjectProvider to the service.
     * @param provider The ObjectProvider.
     */
    void addObjectProvider(ObjectProvider<T> provider);

    /**
     * Remove an ObjectProvider from the service.
     * @param provider The ObjectProvider.
     * @return True if the provider was found and removed, otherwise false.
     */
    boolean removeObjectProvider(ObjectProvider<T> provider);
}
