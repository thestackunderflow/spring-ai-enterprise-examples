# Episode 04 — Switch AI Providers Without Rewriting Your App

One `ChatClient`, swappable `ChatModel` beans. Your code talks to the `ChatModel` *interface* — never a vendor SDK — so changing providers is a configuration decision, not a rewrite.

📺 Video: [youtu.be/Az79qqNDiHo](https://youtu.be/Az79qqNDiHo) · 📄 Tutorial: [thestackunderflow.com/tutorials/model-portability](https://thestackunderflow.com/tutorials/model-portability)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :04-model-portability:bootRun
```

Without a key the app still starts and prints the point. To run the **same code** against a local model, start [Ollama](https://ollama.com) (`ollama pull llama3.2`), set `spring.ai.model.chat=ollama`, and re-run — no code change.

## The whole idea

```java
// Inject the INTERFACE, not a concrete model. Config decides which one is wired.
public ModelPortabilityDemo(ChatModel chatModel) {
    this.chatClient = ChatClient.builder(chatModel).build();
}

chatClient.prompt().user("...").call().content();   // this line never changes
```

```properties
spring.ai.model.chat=openai   # cloud
spring.ai.model.chat=ollama   # local, on-box — same code, different property
```

## Running two providers at once

Need cloud **and** local in the same app (e.g. route sensitive prompts on-box)? Keep both starters on the classpath and pick per injection point:

```java
public MyService(@Qualifier("openAiChatModel") ChatModel cloud,
                 @Qualifier("ollamaChatModel") ChatModel local) { ... }
```

Or mark one bean `@Primary` as the default and `@Qualifier` the other explicitly. The calling code is identical — only the wiring differs.

## Files
- [`Ep04Application.java`](src/main/java/com/thestackunderflow/springai/ep04/Ep04Application.java) — a plain Spring Boot app.
- [`ModelPortabilityDemo.java`](src/main/java/com/thestackunderflow/springai/ep04/ModelPortabilityDemo.java) — injects the `ChatModel` interface; the call never changes.
- [`application.properties`](src/main/resources/application.properties) — `spring.ai.model.chat` selects the provider.
