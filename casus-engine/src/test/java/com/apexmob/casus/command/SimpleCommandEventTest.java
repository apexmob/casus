package com.apexmob.casus.command;

import com.apexmob.casus.Character;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleCommandEventTest {

    private CommandEvent event;
    private Character character;

    @Before
    public void before() {
        character = Mockito.mock(Character.class);
        event = new SimpleCommandEvent(character, "test1 test2");
    }

    @Test
    public void testConstructor_whenConstructed_thenAllReadable() {
        assertSame(character, event.getCharacter());
        assertEquals("test1 test2", event.toString());
        assertEquals(2, event.getArguments().length);
        assertEquals("test1", event.getArguments()[0]);
        assertEquals("test2", event.getArguments()[1]);
        assertSame(character, event.getGameObject());
    }
}
