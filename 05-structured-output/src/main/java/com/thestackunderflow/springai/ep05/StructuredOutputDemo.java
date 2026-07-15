package com.thestackunderflow.springai.ep05;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

/**
 * {@code .entity(...)} is Jackson-for-models: it turns the model's text into typed Java.
 *
 * <ul>
 *   <li>{@code .entity(ActorFilms.class)} → one typed record (schema generated from the record).</li>
 *   <li>{@code .entity(new ParameterizedTypeReference<List<ActorFilms>>(){})} → a typed collection.
 *       (Java erases generics at runtime, so a bare {@code List.class} can't express {@code List<ActorFilms>};
 *       the {@code ParameterizedTypeReference} carries the element type.)</li>
 * </ul>
 *
 * <p>Spring AI 2.0.0 also adds an optional schema-validation step —
 * {@code .entity(ActorFilms.class, spec -> spec.validateSchema())} — that retries automatically if the
 * model's shape drifts, instead of throwing on a bad parse. See the README.
 *
 * <p>The rule that matters: never pipe free text downstream — pipe typed, schema-checked objects.
 */
@Component
public class StructuredOutputDemo implements CommandLineRunner {

    private final ChatClient chatClient;

    public StructuredOutputDemo(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep05] No OPENAI_API_KEY set — skipping the live call. The shapes:
                           ActorFilms one = chatClient.prompt().user("...").call().entity(ActorFilms.class);
                           List<ActorFilms> many = chatClient.prompt().user("...")
                                   .call().entity(new ParameterizedTypeReference<>() {});
                           // 2.0.0: .entity(ActorFilms.class, spec -> spec.validateSchema()) retries a bad shape
                    """);
            return;
        }

        // One typed record — no manual JSON parsing anywhere.
        ActorFilms one = chatClient.prompt()
                .user("Generate the filmography for a random actor.")
                .call()
                .entity(ActorFilms.class);
        System.out.println("\n[ep05] one → " + one.actor() + ": " + one.movies());

        // A typed collection — ParameterizedTypeReference carries the element type past generics erasure.
        List<ActorFilms> many = chatClient.prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {});
        many.forEach(af -> System.out.println("[ep05] many → " + af.actor() + ": " + af.movies()));
        System.out.println();
    }
}
