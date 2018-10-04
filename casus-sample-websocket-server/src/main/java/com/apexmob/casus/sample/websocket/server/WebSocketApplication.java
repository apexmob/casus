package com.apexmob.casus.sample.websocket.server;

import com.apexmob.casus.GameEngine;
import com.apexmob.casus.sample.SampleGameEngine;
import com.apexmob.casus.server.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Chris Kirk
 * @since 1.0
 */
@SpringBootApplication
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }

    @Bean
    public GameEngine sampleGameEngine() {
        SampleGameEngine sampleGameEngine = new SampleGameEngine();
        sampleGameEngine.add(new SampleWebSocketModule());
        return sampleGameEngine;
    }

}
