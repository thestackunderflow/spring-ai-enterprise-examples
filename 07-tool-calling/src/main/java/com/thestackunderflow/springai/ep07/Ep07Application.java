package com.thestackunderflow.springai.ep07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 7 — "Tool Calling in Spring AI: When AI Calls Your Java Services".
 *
 * The point: annotate a plain Java method with {@code @Tool}, hand the object to the ChatClient, and the
 * model can decide to call it — Spring AI binds the JSON arguments straight onto your method signature.
 */
@SpringBootApplication
public class Ep07Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep07Application.class, args);
    }
}
