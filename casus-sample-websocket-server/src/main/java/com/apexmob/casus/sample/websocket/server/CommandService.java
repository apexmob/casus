package com.apexmob.casus.sample.websocket.server;

import com.apexmob.casus.sample.SampleEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommandService {

    private SimpMessagingTemplate template;

    public CommandService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void send(String character) {
        SampleEvent event = new SampleEvent();
        event.setName(character);
        template.convertAndSendToUser(character, "/queue/sample-events", event);
    }
}
