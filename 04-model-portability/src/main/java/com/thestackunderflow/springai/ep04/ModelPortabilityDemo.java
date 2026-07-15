package com.thestackunderflow.springai.ep04;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The whole trick, in one class: this code depends only on the {@link ChatModel} interface
 * (org.springframework.ai.chat.model.ChatModel). Spring wires whichever concrete implementation the
 * config selected — {@code OpenAiChatModel} or {@code OllamaChatModel} — and this line never changes:
 *
 * <pre>chatClient.prompt().user("...").call().content();</pre>
 *
 * <p><b>Flip the provider with one property, zero code change:</b>
 * <pre>
 *   spring.ai.model.chat=openai   # cloud
 *   spring.ai.model.chat=ollama   # local, on-box (data never leaves the machine)
 * </pre>
 *
 * <p><b>Need both at once?</b> Keep both starters on the classpath and select per injection point with
 * {@code @Qualifier}, or mark one bean {@code @Primary}. The calling code is identical either way — see
 * the README.
 */
@Component
public class ModelPortabilityDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    // Inject the ChatModel *interface* — not OpenAiChatModel, not OllamaChatModel. Whichever concrete
    // bean `spring.ai.model.chat` selected is the one Spring hands us.
    public ModelPortabilityDemo(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        boolean cloud = key != null && !key.isBlank();
        if (!cloud) {
            System.out.println("""

                    [ep04] No OPENAI_API_KEY set — skipping the live call. The point stands without it:
                           this class injected the ChatModel *interface* and built a ChatClient from it.
                           Set spring.ai.model.chat=ollama (with a local Ollama) to run the SAME code
                           against an on-box model — no code change, just a different property.
                    """);
            return;
        }
        String answer = chatClient.prompt()
                .user("In one sentence, what makes an interface a good seam in software design?")
                .call()
                .content();
        System.out.println("\n[ep04] " + answer + "\n");
    }
}
