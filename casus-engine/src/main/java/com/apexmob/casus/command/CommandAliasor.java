package com.apexmob.casus.command;
//TODO docs
public interface CommandAliasor {

    boolean canAlias(CommandEvent commandEvent);

    CommandEvent alias(CommandEvent commandEvent);
}
