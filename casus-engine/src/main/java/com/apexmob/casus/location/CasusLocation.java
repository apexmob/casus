package com.apexmob.casus.location;

import com.apexmob.casus.attribute.CharacterHolder;
import com.apexmob.casus.attribute.SimpleCharacterHolder;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class CasusLocation extends SimpleLocation {

    public CasusLocation() {
        super();

        initializeAttributes();
    }

    /**
     * Initialize all attributes for the object.
     */
    protected void initializeAttributes() {
        putAttribute(CharacterHolder.class, new SimpleCharacterHolder());
    }
}
