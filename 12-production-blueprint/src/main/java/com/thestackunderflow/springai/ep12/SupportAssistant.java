package com.thestackunderflow.springai.ep12;

import java.time.LocalDate;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

/**
 * A production-style assistant assembled from everything the series covered:
 * <ul>
 *   <li>a <b>system prompt</b> (behaviour + guardrail),</li>
 *   <li>an <b>advisor stack</b> — here {@link SimpleLoggerAdvisor} for audit/observability; in production
 *       add {@code QuestionAnswerAdvisor.builder(vectorStore).build()} (RAG, Ep 6) and
 *       {@code MessageChatMemoryAdvisor.builder(chatMemory).build()} (memory),</li>
 *   <li><b>tools</b> the model can call — the {@code @Tool} methods below (Ep 7).</li>
 * </ul>
 */
@Service
public class SupportAssistant {

    private final ChatClient chatClient;

    public SupportAssistant(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are an enterprise support assistant. Be concise, cite tools when you use "
                        + "them, and never invent order data — call a tool instead.")
                .defaultAdvisors(new SimpleLoggerAdvisor())   // audit + observability on every call
                .defaultTools(this)                            // the @Tool methods below
                .build();
    }

    @Tool(description = "Look up the delivery status for an order id")
    String orderStatus(String orderId) {
        // A real service hits your order system; here we return a deterministic stub.
        return "Order " + orderId + " shipped, arriving " + LocalDate.now().plusDays(2);
    }

    public String ask(String question) {
        return chatClient.prompt().user(question).call().content();
    }
}
