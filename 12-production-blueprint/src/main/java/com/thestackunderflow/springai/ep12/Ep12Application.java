package com.thestackunderflow.springai.ep12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 12 — "Build a Production-Style Spring AI App: The End-to-End Enterprise Blueprint".
 *
 * The whole series assembled onto one {@code ChatClient}: a system prompt, an advisor stack (logging/audit,
 * and in production a QuestionAnswerAdvisor for RAG + a MessageChatMemoryAdvisor for conversation memory),
 * and {@code @Tool}s the model can call. This module shows the composition with the confirmed core APIs;
 * the RAG/memory advisors are wired identically (see the README) once their modules are on the classpath.
 */
@SpringBootApplication
public class Ep12Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep12Application.class, args);
    }
}
