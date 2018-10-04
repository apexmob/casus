package com.apexmob.casus.character;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCharacterTest {

    @Test
    public void testConstructor_whenNameProvided_thenNameReadable() {
        assertEquals("name", new SimpleCharacter("name").getName());
    }
}
