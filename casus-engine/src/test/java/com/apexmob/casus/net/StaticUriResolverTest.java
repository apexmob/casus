package com.apexmob.casus.net;

import org.junit.Test;

import java.net.URI;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class StaticUriResolverTest {

    @Test
    public void testConstructorUri_whenUriProvided_thenUriReadable() {
        URI uri = URI.create("http://test.com/test");
        assertSame(uri, new StaticUriResolver(uri).resolveUri());
    }

    @Test
    public void testConstructorString_whenStringProvided_thenUriReadable() {
        assertEquals("http://test.com/test", new StaticUriResolver("http://test.com/test").resolveUri().toString());
    }

}
