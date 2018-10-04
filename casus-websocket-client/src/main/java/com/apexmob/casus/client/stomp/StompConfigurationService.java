package com.apexmob.casus.client.stomp;

import com.apexmob.casus.Service;
import com.apexmob.casus.net.UriResolver;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * StompConfigurationService represents the basic interface for managing all of the configuration necessary for a Stomp
 * web socket client implementation.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public interface StompConfigurationService extends Service {

    /**
     * Retrieve a resolver that can provide a URI to the web socket.
     * @return The URI resolver.
     */
    UriResolver getUriResolver();

    /**
     * Set the resolver for providing a URI to the web socket.
     * @param resolver The URI resolver.
     */
    void setUriResolver(UriResolver resolver);

    /**
     * Retrieve the subscriptions that the client is interested in.
     * @return The collection of subscriptions.
     */
    Collection<String> getSubscriptions();

    /**
     * Add a subscription.
     * @param subscription The subscription to add.
     */
    void addSubscription(String subscription);

    /**
     * Remove a subscription.
     * @param subscription The subscription to remove.
     * @return True if the list of subscriptions changed as a result of the removal, otherwise false.
     */
    boolean removeSubscription(String subscription);

    /**
     * Map a class/type to a stomp destination.
     * @param destination The stomp destination.
     * @param type The class/type.
     */
    void putMapping(String destination, Type type);

    /**
     * Remove a class/type mapping for a destination.
     * @param destination The stomp destination.
     * @return The class/type if removed, otherwise null;
     */
    Type removeMapping(String destination);

    /**
     * Retrieve the class/type for the provided destination.
     * @param destination The stomp destination.
     * @return The class/type.
     */
    Type resolve(String destination);

    /**
     * Set the web socket container provider.
     * @param provider The provider.
     */
    void setWebSocketContainerProvider(WebSocketContainerProvider provider);

    /**
     * Get the web socket container provider.
     * @return The provider.
     */
    WebSocketContainerProvider getWebSocketContainerProvider();
}
