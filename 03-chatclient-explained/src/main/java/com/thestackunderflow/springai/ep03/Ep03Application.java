package com.thestackunderflow.springai.ep03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 3 — "Spring AI ChatClient, Explained Step by Step".
 *
 * Walks the fluent chain prompt → system/user → call → content/chatResponse, and shows that the
 * response carries its own receipt: the model name and token usage. See {@link ChatClientExplainedDemo}.
 */
@SpringBootApplication
public class Ep03Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep03Application.class, args);
    }
}
