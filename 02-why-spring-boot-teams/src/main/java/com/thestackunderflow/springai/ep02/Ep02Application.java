package com.thestackunderflow.springai.ep02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 2 — "Why Spring AI Fits Spring Boot Teams (You Already Know 80% of It)".
 *
 * The point: the AI-calling service ({@link SupportService}) is an ordinary Spring bean that
 * constructor-injects a {@code ChatClient.Builder} — the same move as injecting a repository.
 */
@SpringBootApplication
public class Ep02Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep02Application.class, args);
    }
}
