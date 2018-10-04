package com.apexmob.casus.character;

import com.apexmob.casus.Character;
import com.apexmob.casus.attribute.Locatable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class CasusCharacterTest {

    @Test
    public void testEmptyConstuctor_whenCreated_thenAttributesInitialized() {
        Character character = new CasusCharacter();
        assertTrue(character.supportsAttribute(Locatable.class));
    }

    @Test
    public void testNameConstuctor_whenCreated_thenAttributesInitialized() {
        Character character = new CasusCharacter("name");
        assertTrue(character.supportsAttribute(Locatable.class));
    }

    @Test
    public void testNameConstuctor_whenCreated_thenNamePopulated() {
        Character character = new CasusCharacter("name");
        assertEquals("name", character.getName());
    }
}
