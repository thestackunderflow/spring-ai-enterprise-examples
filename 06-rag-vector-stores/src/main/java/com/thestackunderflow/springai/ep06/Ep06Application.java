package com.thestackunderflow.springai.ep06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 6 — "RAG with Spring AI: Turn Enterprise Docs Into Searchable AI Memory".
 *
 * The point: embed your documents into a VectorStore, then let QuestionAnswerAdvisor retrieve the
 * relevant ones and add them to the prompt — so the model answers from YOUR governed content, grounded.
 */
@SpringBootApplication
public class Ep06Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep06Application.class, args);
    }
}
