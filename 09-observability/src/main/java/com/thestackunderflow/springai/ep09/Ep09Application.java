package com.thestackunderflow.springai.ep09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 9 — "Observability for Spring AI: Why Every Model Call Needs a Trace".
 *
 * The point: a {@code SimpleLoggerAdvisor} on the ChatClient logs every request/response, and Spring AI's
 * Micrometer observations (via spring-boot-starter-actuator) expose token usage and latency as metrics.
 */
@SpringBootApplication
public class Ep09Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep09Application.class, args);
    }
}
