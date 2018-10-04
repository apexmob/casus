package com.apexmob.casus.net;

import java.net.URI;

/**
 * StaticUriResolver is a simple implementation of the {@link UriResolver} that resolves to a static value.
 *
 * @author Chris Kirk
 * @since 1.0
 */
public class StaticUriResolver implements UriResolver {

    private final URI url;

    /**
     * Construct a new instance with the provided URI.
     * @param url The URI.
     */
    public StaticUriResolver(URI url) {
        this.url = url;
    }

    /**
     * Construct a new instance with the provided uri.
     * @param uri The uri.
     */
    public StaticUriResolver(String uri) {
        this(URI.create(uri));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI resolveUri() {
        return url;
    }
}
