package com.apexmob.casus.sample.websocket.server;

import com.apexmob.casus.GameEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class EventController {

    private final CommandService commandService;

    public EventController(CommandService commandService) {
        this.commandService = commandService;
    }

//    @MessageMapping("/commands")
//    public void greeting(GameEvent event, Principal principal) throws Exception {
//        System.out.println("Event Received for " + principal.getName());
//        commandService.send(principal.getName());
//    }

}
