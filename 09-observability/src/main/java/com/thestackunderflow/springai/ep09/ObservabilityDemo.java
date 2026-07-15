package com.thestackunderflow.springai.ep09;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Two layers of observability, both free:
 * <ul>
 *   <li><b>{@link SimpleLoggerAdvisor}</b> on the client logs every prompt + response (set
 *       {@code logging.level.org.springframework.ai.chat.client.advisor=DEBUG}).</li>
 *   <li>The typed {@link ChatResponse} metadata carries the model name and token usage — and with
 *       spring-boot-starter-actuator on the classpath, Spring AI records those as Micrometer metrics
 *       (operation duration, token usage) you can scrape/trace.</li>
 * </ul>
 */
@Component
public class ObservabilityDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    public ObservabilityDemo(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep09] No OPENAI_API_KEY set — skipping the live call. The shape:
                           ChatClient.builder(model).defaultAdvisors(new SimpleLoggerAdvisor()).build();
                           var resp = chatClient.prompt().user("...").call().chatResponse();
                           resp.getMetadata().getModel();   // which model answered
                           resp.getMetadata().getUsage();   // prompt / completion / total tokens
                    """);
            return;
        }
        ChatResponse resp = chatClient.prompt()
                .user("In one sentence, why does every model call need a trace?")
                .call()
                .chatResponse();
        System.out.println("\n[ep09] model=" + resp.getMetadata().getModel()
                + " usage=" + resp.getMetadata().getUsage()
                + "\n[ep09] " + resp.getResult().getOutput().getText() + "\n");
    }
}
