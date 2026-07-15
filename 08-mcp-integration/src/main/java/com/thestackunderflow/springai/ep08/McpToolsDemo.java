package com.thestackunderflow.springai.ep08;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Locally, the {@link CalculatorService} @Tool methods are callable exactly like Episode 7. MCP's only
 * job is to make these same methods callable <b>across</b> processes over a standard protocol — the tool
 * contract never changes.
 *
 * <p>As an MCP <b>client</b>, external tools arrive as a {@code ToolCallbackProvider}; hand them to the
 * client with {@code ChatClient.Builder.defaultTools(toolCallbackProvider)} (in 2.0.0
 * {@code defaultToolCallbacks(...)} is deprecated). See the README.
 */
@Component
public class McpToolsDemo implements CommandLineRunner {

    private final ChatClient chatClient;
    private final CalculatorService calculator;

    public McpToolsDemo(ChatClient.Builder builder, CalculatorService calculator) {
        this.chatClient = builder.build();
        this.calculator = calculator;
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep08] No OPENAI_API_KEY set — skipping the live call. The point stands:
                           CalculatorService's @Tool methods are auto-discovered as an MCP server's tools.
                           Same @Tool as Episode 7 — MCP just makes them callable over a standard transport.
                    """);
            return;
        }
        String answer = chatClient.prompt()
                .user("What is the square root of (23 times 8)? Use the tools.")
                .tools(calculator)   // the same @Tool methods an MCP server would expose
                .call()
                .content();
        System.out.println("\n[ep08] " + answer + "\n");
    }
}
