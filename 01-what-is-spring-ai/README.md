# Episode 01 — What Is Spring AI?

The smallest possible Spring AI program: an injected, auto-configured model bean and a fluent call.

📺 Video: _(link on publish)_ · 📄 Tutorial: [thestackunderflow.com/tutorials/what-is-spring-ai](https://thestackunderflow.com/tutorials/what-is-spring-ai)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :01-what-is-spring-ai:bootRun
```

Without a key, the app still starts and prints the call shape instead of making a request.

## The whole idea

```java
public WhatIsSpringAiDemo(OpenAiChatModel model) {   // DI + auto-config: Spring built the bean
    this.chatClient = ChatClient.create(model);      // wrap it once
}

String answer = chatClient.prompt()                  // fluent API, same shape as RestClient
        .user("Why is the sky blue? Answer in two sentences.")
        .call()
        .content();
```

That's it — dependency injection, auto-configuration, and a fluent chain. AI adopted *inside* the patterns
your team already uses, not around them.

## Files
- [`Ep01Application.java`](src/main/java/com/thestackunderflow/springai/ep01/Ep01Application.java) — a plain Spring Boot app.
- [`WhatIsSpringAiDemo.java`](src/main/java/com/thestackunderflow/springai/ep01/WhatIsSpringAiDemo.java) — the ChatClient call.
- [`application.properties`](src/main/resources/application.properties) — key from env, model on the flattened 2.0.0 property path.
