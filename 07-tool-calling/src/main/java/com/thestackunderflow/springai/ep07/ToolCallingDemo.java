package com.thestackunderflow.springai.ep07;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Hand the tools to the ChatClient and ask a question that needs them. The model decides to call
 * {@code getCurrentDateTime()}, Spring AI runs your Java, feeds the result back, and the model answers —
 * you stay in control because it's your code that runs.
 */
@Component
public class ToolCallingDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    public ToolCallingDemo(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep07] No OPENAI_API_KEY set — skipping the live call. The shape:
                           chatClient.prompt()
                               .user("What day is it today?")
                               .tools(new DateTimeTools())   // your @Tool methods, callable by the model
                               .call().content();
                    """);
            return;
        }
        String answer = chatClient.prompt()
                .user("What day and time is it right now?")
                .tools(new DateTimeTools())
                .call()
                .content();
        System.out.println("\n[ep07] " + answer + "\n");
    }
}
