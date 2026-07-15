package com.thestackunderflow.springai.ep12;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runs the {@link SupportAssistant} — one injected service that composes system prompt + advisor stack +
 * tools, exactly the way an ordinary Spring {@code @Service} composes its collaborators.
 */
@Component
public class ProductionBlueprintDemo implements CommandLineRunner {

    private final SupportAssistant assistant;

    public ProductionBlueprintDemo(SupportAssistant assistant) {
        this.assistant = assistant;
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep12] No OPENAI_API_KEY set — skipping the live call. The blueprint (SupportAssistant):
                           ChatClient.builder(model)
                               .defaultSystem("...")                          // behaviour + guardrail
                               .defaultAdvisors(new SimpleLoggerAdvisor())    // audit/observability (+RAG, +memory)
                               .defaultTools(this)                            // @Tool methods the model can call
                               .build();
                    """);
            return;
        }
        System.out.println("\n[ep12] " + assistant.ask("Where is order A-1042?") + "\n");
    }
}
