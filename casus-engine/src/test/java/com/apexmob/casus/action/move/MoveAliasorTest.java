package com.apexmob.casus.action.move;

import com.apexmob.casus.Character;
import com.apexmob.casus.command.CommandEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class MoveAliasorTest {

    private MoveAliasor aliasor;
    private Character character;
    private CommandEvent event;

    @Before
    public void before() {
        aliasor = new MoveAliasor();

        character = Mockito.mock(Character.class);
        event = Mockito.mock(CommandEvent.class);
    }

    @Test
    public void testAlias_whenNotAlias_thenReturnOriginal() {
        when(event.getArguments()).thenReturn(new String[]{"no match"});

        assertSame(event, aliasor.alias(event));
    }

    @Test
    public void testCanAlias_whenNotAlias_thenReturnFalse() {
        when(event.getArguments()).thenReturn(new String[]{"no match"});

        assertFalse(aliasor.canAlias(event));
    }

    @Test
    public void testAlias_whenAlias_thenAliasEvent() {
        assertAliased("e", "move east");
        assertAliased("east", "move east");
        assertAliased("w", "move west");
        assertAliased("west", "move west");
        assertAliased("n", "move north");
        assertAliased("north", "move north");
        assertAliased("s", "move south");
        assertAliased("south", "move south");
        assertAliased("u", "move up");
        assertAliased("up", "move up");
        assertAliased("d", "move down");
        assertAliased("down", "move down");
    }

    private void assertAliased(String cmd, String expected) {
        when(event.toString()).thenReturn(cmd);

        assertEquals(expected, aliasor.alias(event).toString());
    }
}
