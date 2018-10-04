package com.apexmob.casus.command;

import com.apexmob.casus.Character;
import com.apexmob.casus.Service;

public interface CommandService extends Service {

    void sendCommand(Character character, String commandStr);

    void addCommandAliasor(CommandAliasor commandAliasor);

    boolean removeCommandAliasor(CommandAliasor commandAliasor);
}
