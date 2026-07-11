package com.thestackunderflow.springai.ep02;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

/**
 * A plain Spring {@code @Service} that happens to talk to a model.
 *
 * <p>No special base class, no framework annotations beyond {@code @Service}. It constructor-injects a
 * {@link ChatClient.Builder} — which Spring AI's auto-configuration provides as a prototype-scoped bean
 * (a fresh builder per injection point) — exactly the way you'd inject an {@code OrderRepository}.
 */
@Service
public class SupportService {

    private final ChatClient chatClient;

    public SupportService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String answer(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
