package com.apexmob.casus.command.listener;


import com.apexmob.casus.GameEventListener;
import com.apexmob.casus.command.CommandEvent;

public abstract class FilteringCommandListener<T extends CommandEvent> implements GameEventListener<T> {

    @Override
    public boolean onEvent(T commandEvent) {
        boolean retVal = false;
        if (isProcessable(commandEvent)) {
            retVal = doExecute(commandEvent);
        }
        return retVal;
    }

    protected abstract boolean isProcessable(T commandEvent);

    protected abstract boolean doExecute(T commandEvent);
}
