package com.thestackunderflow.springai.ep03;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The ChatClient mental model, made concrete.
 *
 * <p>{@code .content()} gives you just the text; {@code .chatResponse()} gives you the whole envelope —
 * including which model answered and how many tokens it used. That receipt is your cost meter on day one,
 * before you write a line of cost-tracking code.
 */
@Component
public class ChatClientExplainedDemo implements CommandLineRunner {

    private final org.springframework.ai.chat.client.ChatClient chatClient;

    public ChatClientExplainedDemo(OpenAiChatModel model) {
        this.chatClient = org.springframework.ai.chat.client.ChatClient.create(model);
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep03] No OPENAI_API_KEY set — skipping the live call. The shape:
                           var resp = chatClient.prompt().system("...").user("...").call().chatResponse();
                           resp.getMetadata().getModel();   // which model answered
                           resp.getMetadata().getUsage();   // prompt / completion / total tokens
                    """);
            return;
        }

        // Ask for the whole envelope, not just the text.
        ChatResponse response = chatClient.prompt()
                .system("You are concise. Answer in two sentences.")
                .user("Why is the sky blue?")
                .call()
                .chatResponse();

        String answer = response.getResult().getOutput().getText();
        Usage usage = response.getMetadata().getUsage();

        System.out.println("\n[ep03] Answer: " + answer);
        System.out.println("[ep03] Model: " + response.getMetadata().getModel());
        System.out.printf("[ep03] Tokens — prompt: %d, completion: %d, total: %d%n%n",
                usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
    }
}
