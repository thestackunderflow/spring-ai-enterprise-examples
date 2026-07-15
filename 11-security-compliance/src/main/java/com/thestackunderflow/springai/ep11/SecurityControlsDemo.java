package com.thestackunderflow.springai.ep11;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Control 1 (Secrets) is config: the key is bound from {@code ${OPENAI_API_KEY}} in
 * {@code application.properties} — it never lives in code or the repo.
 *
 * <p>Control 4 (Audit) is this advisor: {@link SimpleLoggerAdvisor} records every call (who/what/when),
 * and Spring AI's Micrometer observations add token/latency metrics — your compliance evidence trail.
 *
 * <p>The data-boundary control (a {@code SafeGuardAdvisor} that blocks sensitive words before the prompt
 * reaches the model, plus a {@code ModerationModel} for PII) chains in exactly the same way — see README.
 */
@Component
public class SecurityControlsDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    public SecurityControlsDemo(ChatClient.Builder builder) {
        // Audit control: every call flows through the logger advisor (your evidence trail).
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep11] No OPENAI_API_KEY set — skipping the live call. The controls in code here:
                           (1) Secrets — key bound from ${OPENAI_API_KEY}, never in source.
                           (4) Audit  — SimpleLoggerAdvisor on the chain logs who/what/when/tokens.
                           Data-boundary (SafeGuardAdvisor + ModerationModel) and Authz (Spring Security +
                           in-@Tool checks) are documented in the README.
                    """);
            return;
        }
        String answer = chatClient.prompt()
                .user("In one sentence, why should an API key never live in source control?")
                .call()
                .content();
        System.out.println("\n[ep11] " + answer + "\n");
    }
}
