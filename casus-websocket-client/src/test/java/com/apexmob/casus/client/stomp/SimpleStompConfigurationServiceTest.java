package com.apexmob.casus.client.stomp;

import com.apexmob.casus.net.UriResolver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleStompConfigurationServiceTest {

    private SimpleStompConfigurationService service;

    @Before
    public void before() {
        service = new SimpleStompConfigurationService();
    }

    @Test
    public void testGetUriResolver_whenNotSet_thenReturnNull() {
        assertNull(service.getUriResolver());
    }

    @Test
    public void testGetUriResolver_whenSet_thenReturnUriResolver() {
        UriResolver resolver = Mockito.mock(UriResolver.class);

        service.setUriResolver(resolver);

        assertSame(resolver, service.getUriResolver());
    }

    @Test
    public void testGetWebSocketContainerProvider_whenNotSet_thenReturnDefaultImplementation() {
        assertNotNull(service.getWebSocketContainerProvider());
    }

    @Test
    public void testGetWebSocketContainerProvider_whenSet_thenReturnWebSocketContainerProvider() {
        WebSocketContainerProvider provider = Mockito.mock(WebSocketContainerProvider.class);

        service.setWebSocketContainerProvider(provider);

        assertSame(provider, service.getWebSocketContainerProvider());
    }

    @Test
    public void testResolve_whenMappingSet_thenReturnType() {
        service.putMapping("test", String.class);

        assertSame(String.class, service.resolve("test"));
    }

    @Test
    public void testResolve_whenMappingNotSet_thenReturnNull() {
        assertNull(service.resolve("test"));
    }

    @Test
    public void testResolve_whenMappingRemoved_thenReturnNull() {
        service.putMapping("test", String.class);

        service.removeMapping("test");
        assertNull(service.resolve("test"));
    }

    @Test
    public void testRemoveMapping_whenMappingSet_thenReturnType() {
        service.putMapping("test", String.class);

        assertSame(String.class, service.removeMapping("test"));
    }

    @Test
    public void testRemoveMapping_whenMappingNotSet_thenReturnNull() {
        assertNull(service.removeMapping("test"));
    }

    @Test
    public void testGetSubscriptions_whenAdded_thenReturnIncludesSubscription() {
        service.addSubscription("test");

        assertEquals(1, service.getSubscriptions().size());
    }

    @Test
    public void testGetSubscriptions_whenAddedTwice_thenReturnIncludesOneSubscription() {
        service.addSubscription("test");
        service.addSubscription("test");

        assertEquals(1, service.getSubscriptions().size());
    }

    @Test
    public void testGetSubscriptions_whenNotAdded_thenReturnEmptyCollection() {
        assertEquals(0, service.getSubscriptions().size());
    }

    @Test
    public void testGetSubscriptions_whenRemoved_thenNotIncludedInSubscription() {
        service.addSubscription("test");
        service.removeSubscription("test");

        assertEquals(0, service.getSubscriptions().size());
    }

    @Test
    public void testRemoveSubscription_whenAdded_thenReturnTrue() {
        service.addSubscription("test");

        assertTrue(service.removeSubscription("test"));
    }

    @Test
    public void testRemoveSubscriptions_whenNotAdded_thenReturnFalse() {
        assertFalse(service.removeSubscription("test"));
    }
}
