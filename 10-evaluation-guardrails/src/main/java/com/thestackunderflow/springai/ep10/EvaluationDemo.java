package com.thestackunderflow.springai.ep10;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The evaluation loop, made explicit: an AI grading an AI.
 *
 * <ol>
 *   <li>Get an answer from the model.</li>
 *   <li>Make a <b>second, independent</b> model call whose only job is to judge that answer — asked one
 *       narrow question, forced to reply PASS or FAIL.</li>
 *   <li>Turn the verdict into a boolean. In a JUnit test that boolean is your release gate:
 *       {@code assertTrue(gate.isRelevant(question, answer))}.</li>
 * </ol>
 *
 * <p>This is exactly what Spring AI's built-in {@code RelevancyEvaluator} / {@code FactCheckingEvaluator}
 * do behind the {@code Evaluator} interface ({@code evaluate(EvaluationRequest) -> EvaluationResponse.isPass()}).
 * Doing it by hand here shows the mechanism; use the built-ins in real tests — see the README.
 */
@Component
public class EvaluationDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    public EvaluationDemo(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /** The pass/fail gate you would assert on inside a JUnit test. */
    boolean isRelevant(String question, String answer) {
        String verdict = chatClient.prompt()
                .system("You are a strict evaluator. Judge whether the ANSWER actually and correctly "
                        + "answers the QUESTION. Reply with exactly one word: PASS or FAIL.")
                .user("QUESTION: " + question + "\nANSWER: " + answer)
                .call()
                .content();
        return verdict != null && verdict.trim().toUpperCase().startsWith("PASS");
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep10] No OPENAI_API_KEY set — skipping. The release-gate shape (in a JUnit test):
                           String answer = ragPipeline.answer(question);
                           EvaluationResponse r = relevancyEvaluator.evaluate(
                                   new EvaluationRequest(question, retrievedDocs, answer));
                           assertTrue(r.isPass());   // fail the build if the answer wasn't relevant
                    """);
            return;
        }
        String question = "What is the capital of France?";
        String answer = chatClient.prompt().user(question).call().content();
        boolean pass = isRelevant(question, answer);
        System.out.println("\n[ep10] answer -> " + answer);
        System.out.println("[ep10] release gate -> " + (pass ? "PASS ✓" : "FAIL ✗") + "\n");
    }
}
