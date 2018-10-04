package com.apexmob.casus.command.listener;

import com.apexmob.casus.command.CommandEvent;

import java.util.List;

public abstract class FilterByFirstArgumentCommandListener<T extends CommandEvent> extends FilteringCommandListener<T> {

    @Override
    protected boolean isProcessable(T commandEvent) {
        return commandEvent.getArguments().length > 0 && getProcessableFirstArguments().contains(commandEvent.getArguments()[0]);
    }

    protected abstract List<String> getProcessableFirstArguments();
}
