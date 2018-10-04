package com.apexmob.casus;

import com.apexmob.casus.ObjectProvider;
import com.apexmob.casus.SimpleObjectService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URI;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleObjectServiceTest {

    private SimpleObjectService service;
    private ObjectProvider provider1 = Mockito.mock(ObjectProvider.class);
    private ObjectProvider provider2 = Mockito.mock(ObjectProvider.class);

    @Before
    public void before() {
        service = new SimpleObjectService();

        when(provider1.getObject(eq(URI.create("test://test.com/test1")))).thenReturn("test1");
        when(provider2.getObject(eq(URI.create("test://test.com/test2")))).thenReturn("test2");
    }

    @Test
    public void testGetObject_whenNoProviders_thenReturnNull() {
        assertNull(service.getObject(URI.create("test://test.com/fail")));
    }

    @Test
    public void testGetObject_whenProviderDoesNotContainObject_thenReturnNull() {
        service.addObjectProvider(provider1);

        assertNull(service.getObject(URI.create("test://test.com/fail")));
    }

    @Test
    public void testGetObject_whenProviderContainsObject_thenReturnObject() {
        service.addObjectProvider(provider1);

        assertEquals("test1", service.getObject(URI.create("test://test.com/test1")));
    }

    @Test
    public void testGetObject_whenAnyProviderContainsObject_thenReturnObject() {
        service.addObjectProvider(provider1);
        service.addObjectProvider(provider2);

        assertEquals("test2", service.getObject(URI.create("test://test.com/test2")));
    }

    @Test
    public void testRemoveProvider_whenProviderNotPresent_thenReturnFalse() {
        assertFalse(service.removeObjectProvider(provider1));
    }

    @Test
    public void testRemoveProvider_whenProviderPresent_thenReturnTrue() {
        service.addObjectProvider(provider1);

        assertTrue(service.removeObjectProvider(provider1));
    }
}
