package com.apexmob.casus.action.move;

import com.apexmob.casus.command.aliasor.StaticCommandAliasor;

import java.util.HashMap;
import java.util.Map;

/**
 * MoveAliasor is an implementation of the {@link com.apexmob.casus.command.CommandAliasor} interface that aliases movement
 * directions.
 * <p>
 * Aliases the following:
 * <ul>
 * <li>'e', 'east' to 'move east'</li>
 * <li>'w', 'west' to 'move west'</li>
 * <li>'n', 'north' to 'move north'</li>
 * <li>'s', 'south' to 'move south'</li>
 * <li>'u', 'up' to 'move up'</li>
 * <li>'d', 'down' to 'move down'</li>
 * </ul>
 * @author Chris Kirk
 * @since 1.0
 */
public class MoveAliasor extends StaticCommandAliasor {
    public MoveAliasor() {
        super(buildAliases());
    }

    private static final Map<String,String> buildAliases() {
        Map<String,String> retVal = new HashMap<>();

        retVal.put("e", "move east");
        retVal.put("east", "move east");

        retVal.put("w", "move west");
        retVal.put("west", "move west");

        retVal.put("n", "move north");
        retVal.put("north", "move north");

        retVal.put("s", "move south");
        retVal.put("south", "move south");

        retVal.put("u", "move up");
        retVal.put("up", "move up");

        retVal.put("d", "move down");
        retVal.put("down", "move down");

        return retVal;
    }
}
