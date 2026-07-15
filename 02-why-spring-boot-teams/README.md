# Episode 02 — Why Spring AI Fits Spring Boot Teams

The AI-calling service is a plain Spring bean that constructor-injects `ChatClient.Builder` — the same
move you already make with any repository. DI, a starter, and auto-configuration do the rest.

📺 Video: https://youtu.be/3UFXS9jtZPo · 📄 Tutorial: [thestackunderflow.com/tutorials/why-spring-boot-teams](https://thestackunderflow.com/tutorials/why-spring-boot-teams)

## Run it

```bash
export OPENAI_API_KEY=sk-...
./gradlew :02-why-spring-boot-teams:bootRun
```

## The pattern that transfers

```java
@Service
public class SupportService {
    private final ChatClient chatClient;

    // Inject ChatClient.Builder through the constructor — exactly like injecting a repository.
    public SupportService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String answer(String question) {
        return chatClient.prompt().user(question).call().content();
    }
}
```

- **Starter:** `org.springframework.ai:spring-ai-starter-model-openai` (see [`build.gradle.kts`](build.gradle.kts)).
- **Auto-configuration** builds the `OpenAiChatModel` bean and provides a prototype-scoped `ChatClient.Builder`.
- **Config** lives in `application.properties`, keyed off an env var — no secrets in code.

Same DI, same starters, same auto-config, same tests you already trust — pointed at a model.
