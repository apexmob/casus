package com.apexmob.casus.sample;

import com.apexmob.casus.GameEventListener;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SimpleSampleEventListener implements GameEventListener<SampleEvent> {

    public boolean onEvent(SampleEvent event) {
        System.out.println("Event, name=" + event.getName());
        return true;
    }
}
