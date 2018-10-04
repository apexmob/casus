package com.apexmob.casus.command.aliasor;

import com.apexmob.casus.Character;
import com.apexmob.casus.command.CommandEvent;
import com.apexmob.casus.command.CommandAliasor;
import com.apexmob.casus.command.SimpleCommandEvent;

import java.util.Hashtable;
import java.util.Map;

public class FirstArgumentCommandAliasor implements CommandAliasor {

    public static final CommandAliasor NO_ALIASES = new FirstArgumentCommandAliasor(new Hashtable<String, String>());

    private final Map<String,String> aliases = new Hashtable<>();

    public FirstArgumentCommandAliasor(Map<String, String> aliases) {
        this.aliases.putAll(aliases);
    }

    @Override
    public boolean canAlias(CommandEvent commandEvent) {
        return commandEvent.getArguments().length > 0 && aliases.containsKey(commandEvent.getArguments()[0]);
    }

    @Override
    public CommandEvent alias(CommandEvent commandEvent) {
        CommandEvent retVal = commandEvent;

        String newCommandString = aliases.get(commandEvent.getArguments()[0]);
        if (newCommandString != null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(aliases.get(commandEvent.getArguments()[0]));
            if (commandEvent.getArguments().length > 1) {
                for (int i = 1; i < commandEvent.getArguments().length; i++) {
                    buffer.append(' ').append(commandEvent.getArguments()[i]);
                }
            }
            retVal = new SimpleCommandEvent((Character) commandEvent.getGameObject(), buffer.toString());
        }

        return retVal;
    }
}
