package com.thestackunderflow.springai.ep01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 1 — "What Is Spring AI? Enterprise AI Without Leaving the Spring Ecosystem".
 *
 * A normal Spring Boot application. The whole point of the episode: calling a model is an
 * injected, auto-configured bean plus a fluent chain — nothing exotic. See {@link WhatIsSpringAiDemo}.
 */
@SpringBootApplication
public class Ep01Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep01Application.class, args);
    }
}
