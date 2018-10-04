package com.apexmob.casus.command.aliasor;

import com.apexmob.casus.Character;
import com.apexmob.casus.command.CommandEvent;
import com.apexmob.casus.command.CommandAliasor;
import com.apexmob.casus.command.SimpleCommandEvent;

import java.util.Hashtable;
import java.util.Map;

public class StaticCommandAliasor implements CommandAliasor {

    public static final CommandAliasor NO_ALIASES = new StaticCommandAliasor(new Hashtable<String, String>());

    private final Map<String,String> aliases = new Hashtable<>();

    public StaticCommandAliasor(Map<String, String> aliases) {
        this.aliases.putAll(aliases);
    }

    @Override
    public boolean canAlias(CommandEvent commandEvent) {
        return aliases.containsKey(commandEvent.toString().trim());
    }

    @Override
    public CommandEvent alias(CommandEvent commandEvent) {
        CommandEvent retVal = commandEvent;

        String newCommandString = aliases.get(commandEvent.toString().trim());
        if (newCommandString != null) {
            retVal = new SimpleCommandEvent((Character) commandEvent.getGameObject(), newCommandString);
        }

        return retVal;
    }
}
