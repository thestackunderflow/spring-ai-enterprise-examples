package com.thestackunderflow.springai.ep05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 5 — "Stop Parsing Random AI Text: Structured Output to Java POJOs".
 *
 * The point: {@code .entity(...)} turns a model's free-form text into a typed, validated Java object —
 * the way Jackson deserializes JSON — so nothing downstream ever handles raw model text.
 */
@SpringBootApplication
public class Ep05Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep05Application.class, args);
    }
}
