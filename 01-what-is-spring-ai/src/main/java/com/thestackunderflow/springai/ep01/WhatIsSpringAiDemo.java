package com.thestackunderflow.springai.ep01;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The entire idea of Episode 1 in one class.
 *
 * <ul>
 *   <li><b>Dependency injection</b> — Spring hands us the model bean; we never call {@code new}.</li>
 *   <li><b>Auto-configuration</b> — the {@code spring-ai-starter-model-openai} starter built the
 *       {@link OpenAiChatModel} bean for us.</li>
 *   <li><b>Fluent API</b> — {@code prompt().user(...).call().content()}, the same shape as RestClient.</li>
 * </ul>
 *
 * Run with {@code ./gradlew :01-what-is-spring-ai:bootRun} and an {@code OPENAI_API_KEY} set.
 */
@Component
public class WhatIsSpringAiDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    // Spring auto-configured OpenAiChatModel from the starter; we wrap it once into a ChatClient.
    // (You could inject the provider-agnostic ChatModel interface here instead — see Episode 4.)
    public WhatIsSpringAiDemo(OpenAiChatModel model) {
        this.chatClient = ChatClient.create(model);
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep01] No OPENAI_API_KEY set — skipping the live call. The shape is:
                           ChatClient.create(model).prompt().user("...").call().content();
                    """);
            return;
        }

        String answer = chatClient.prompt()
                .user("Why is the sky blue? Answer in two sentences.")
                .call()
                .content();

        System.out.println("\n[ep01] The model answered:\n" + answer + "\n");
    }
}
