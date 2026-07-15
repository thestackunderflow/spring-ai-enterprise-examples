package com.thestackunderflow.springai.ep04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 4 — "Switch AI Providers Without Rewriting Your App: Model Portability".
 *
 * The point: your {@code ChatClient} code talks to the {@code ChatModel} interface, never a vendor SDK.
 * Which concrete {@code ChatModel} is wired underneath is a configuration decision
 * ({@code spring.ai.model.chat=openai|ollama}) — the calling code never changes.
 */
@SpringBootApplication
public class Ep04Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep04Application.class, args);
    }
}
