package com.apexmob.casus.sample;

import com.apexmob.casus.GameEventListener;
import com.apexmob.casus.command.CommandEvent;

/**
 * @author Chris Kirk
 * @since 1.0
 */
public class SampleCommandEventListener implements GameEventListener<CommandEvent> {

    private final SampleEventDispatcher sampleEventDispatcher;

    public SampleCommandEventListener(SampleEventDispatcher sampleEventDispatcher) {
        this.sampleEventDispatcher = sampleEventDispatcher;
    }

    @Override
    public boolean onEvent(CommandEvent event) {
        if ("sample".equals(event.getArguments()[0])) {
            SampleEvent sampleEvent = new SampleEvent();
            sampleEvent.setName(event.getCharacter().getName());

            sampleEventDispatcher.dispatch(sampleEvent);
            return true;
        }
        return false;
    }
}
