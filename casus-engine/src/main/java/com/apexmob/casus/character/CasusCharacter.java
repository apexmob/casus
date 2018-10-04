package com.apexmob.casus.character;

import com.apexmob.casus.attribute.Locatable;
import com.apexmob.casus.attribute.SimpleLocatable;

/**
 * CasusCharacter is a concrete implementation of the {@link com.apexmob.casus.Character} interface that includes
 * all attributes provided by the Casus engine.
 * @author Chris Kirk
 * @since 1.0
 */
public class CasusCharacter extends SimpleCharacter {
    /**
     * Construct a new instance.
     */
    public CasusCharacter() {
        super();

        initializeAttributes();
    }

    /**
     * Construct a new instance with the provided name.
     * @param name The name of the character.
     */
    public CasusCharacter(String name) {
        super(name);

        initializeAttributes();
    }

    /**
     * Initialize all attributes for the object.
     */
    protected void initializeAttributes() {
        putAttribute(Locatable.class, new SimpleLocatable());
    }
}
