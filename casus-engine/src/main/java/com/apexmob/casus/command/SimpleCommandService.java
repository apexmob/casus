package com.apexmob.casus.command;

import com.apexmob.casus.BaseService;
import com.apexmob.casus.Character;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.command.event.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleCommandService extends BaseService implements CommandService {

    private final GameEventDispatcher<PreAliasingCommandEvent> preAliasingCommandEventGameEventDispatcher;
    private final GameEventDispatcher<UnrecognizedCommandEvent> unrecognizedCommandEventGameEventDispatcher;
    private final GameEventDispatcher<PostAliasingCommandEvent> postAliasingCommandEventGameEventDispatcher;
    private final List<CommandAliasor> commandAliasors = new ArrayList<>();

    public SimpleCommandService(GameEventDispatcher<PreAliasingCommandEvent> preAliasingCommandEventGameEventDispatcher, GameEventDispatcher<PostAliasingCommandEvent> postAliasingCommandEventGameEventDispatcher, GameEventDispatcher<UnrecognizedCommandEvent> unrecognizedCommandEventGameEventDispatcher) {
        this.preAliasingCommandEventGameEventDispatcher = preAliasingCommandEventGameEventDispatcher;
        this.unrecognizedCommandEventGameEventDispatcher = unrecognizedCommandEventGameEventDispatcher;
        this.postAliasingCommandEventGameEventDispatcher = postAliasingCommandEventGameEventDispatcher;
    }

    @Override
    public void sendCommand(Character character, String commandStr) {
        CommandEvent cmd = new SimpleCommandEvent(character, commandStr);

        boolean handled = false;

        handled = handled || preAliasingCommandEventGameEventDispatcher.dispatch(new SimplePreAliasingCommandEvent(character, commandStr));

        cmd = alias(new SimpleCommandEvent(character, commandStr));

        handled = handled || postAliasingCommandEventGameEventDispatcher.dispatch(new SimplePostAliasingCommandEvent(character, cmd.toString()));

        if (!handled) {
            unrecognizedCommandEventGameEventDispatcher.dispatch(new SimpleUnrecognizedCommandEvent(character, commandStr));
        }
    }

    protected CommandEvent alias(CommandEvent commandEvent) {
        CommandEvent retVal = commandEvent;

        for (CommandAliasor aliasor : commandAliasors) {
            if (aliasor.canAlias(commandEvent)) {
                retVal = aliasor.alias(commandEvent);
                break;
            }
        }

        return retVal;
    }

    @Override
    public void addCommandAliasor(CommandAliasor commandAliasor) {
        this.commandAliasors.add(commandAliasor);
    }

    @Override
    public boolean removeCommandAliasor(CommandAliasor commandAliasor) {
        return commandAliasors.remove(commandAliasor);
    }
}
