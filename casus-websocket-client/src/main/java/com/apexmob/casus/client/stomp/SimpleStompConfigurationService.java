package com.apexmob.casus.client.stomp;

import com.apexmob.casus.net.UriResolver;

import java.lang.reflect.Type;
import java.util.*;

/**
 * SimpleStompConfigurationService is a simple concrete implementation of the {@link StompConfigurationService} interface.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleStompConfigurationService implements StompConfigurationService {

    private UriResolver uriResolver = null;
    private final Map<String, Type> typeMap = new Hashtable<>();
    private final Set<String> subscriptions = new LinkedHashSet<>();
    private WebSocketContainerProvider provider = new SimpleWebSocketContainerProvider();

    /**
     * {@inheritDoc}
     */
    @Override
    public void putMapping(String destination, Type type) {
        typeMap.put(destination, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type removeMapping(String destination) {
        return typeMap.remove(destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type resolve(String destination) {
        return typeMap.get(destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UriResolver getUriResolver() {
        return uriResolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUriResolver(UriResolver resolver) {
        this.uriResolver = resolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getSubscriptions() {
        return Collections.unmodifiableCollection(subscriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubscription(String subscription) {
        subscriptions.add(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeSubscription(String subscription) {
        return subscriptions.remove(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWebSocketContainerProvider(WebSocketContainerProvider provider) {
        this.provider = provider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebSocketContainerProvider getWebSocketContainerProvider() {
        return provider;
    }
}
