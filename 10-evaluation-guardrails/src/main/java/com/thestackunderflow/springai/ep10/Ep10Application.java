package com.thestackunderflow.springai.ep10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 10 — "Do Not Trust AI Output Blindly: Evaluation, Guardrails & Hallucination Control".
 *
 * The point: close the "it answered, but was it right?" gap with an evaluator — a second, independent
 * model call that judges the first answer and returns pass/fail. Wire it into a JUnit test and it becomes
 * a release gate. Spring AI ships {@code RelevancyEvaluator} and {@code FactCheckingEvaluator} that do
 * exactly this behind the {@code Evaluator} interface; see {@link EvaluationDemo} + the README.
 */
@SpringBootApplication
public class Ep10Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep10Application.class, args);
    }
}
