package com.apexmob.casus.sample;

import com.apexmob.casus.SimpleGameEngine;
import com.apexmob.casus.command.CommandModule;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SampleGameEngine extends SimpleGameEngine {
    public SampleGameEngine() {
        super();

        this.add(new CommandModule());
        this.add(new SampleModule());
    }
}
