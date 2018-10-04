package com.apexmob.casus.server.websocket;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.GameEventDispatcher;
import com.apexmob.casus.command.CommandEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class CommandController {

    private final GameEngine gameEngine;
    private final GameEventDispatcher<CommandEvent> eventDispatcher;

    public CommandController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.eventDispatcher = (GameEventDispatcher<CommandEvent>)gameEngine.getGameEventDispatcher(CommandEvent.class);
    }

    @MessageMapping("/commands")
    public void greeting(CommandEvent event, Principal principal) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (event.getCharacter().getName().equals(principal.getName())) {
            eventDispatcher.dispatch(event);
        }
    }

}
