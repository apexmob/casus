package com.apexmob.casus.client.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Chris Kirk
 * @since 1.0
 */
@SpringBootApplication
@Import(value = {TestWebSocketConfig.class, TestWebSecurityConfig.class})
public class TestWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestWebSocketApplication.class, args);
    }


}
