package com.apexmob.casus;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimpleObjectService is a simple concrete implementation of the {@link ObjectService} interface.
 * @param <T> The type of object that may be retrieved.
 */
public class SimpleObjectService<T> extends BaseService implements ObjectService<T> {

    private final List<ObjectProvider<T>> providers = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObjectProvider(ObjectProvider<T> provider) {
        this.providers.add(provider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeObjectProvider(ObjectProvider<T> provider) {
        return this.providers.remove(provider);
    }

    /**
     * Retrieve the list of ObjectProviders registered in the service.
     * @return The list of ObjectProviders.
     */
    protected List<ObjectProvider<T>> getObjectProviders() {
        return Collections.unmodifiableList(providers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getObject(URI uri) {
        T retVal = null;

        for (ObjectProvider<T> provider : getObjectProviders()) {
            retVal = provider.getObject(uri);
            if (retVal!= null) {
                break;
            }
        }

        return retVal;
    }
}
