package com.thestackunderflow.springai.ep02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runs {@link SupportService} — which was wired entirely by Spring: starter → auto-config →
 * injected {@code ChatClient.Builder}. No {@code new}, no manual model construction.
 */
@Component
public class PatternTransferDemo implements CommandLineRunner {

    private final SupportService support;

    public PatternTransferDemo(SupportService support) {
        this.support = support;
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep02] No OPENAI_API_KEY set — skipping the live call. The point stands without it:
                           SupportService injected ChatClient.Builder via its constructor, like any repository.
                    """);
            return;
        }
        System.out.println("\n[ep02] " + support.answer("In one sentence, what is dependency injection?") + "\n");
    }
}
