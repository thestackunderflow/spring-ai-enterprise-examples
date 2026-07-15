# Episode 09 — Observability for Spring AI

A `SimpleLoggerAdvisor` logs every prompt/response; the typed `ChatResponse` metadata plus Micrometer observations (via actuator) expose token usage and latency as metrics.

📺 Video: https://youtu.be/U0QfnznxF90 · 📄 Tutorial: [thestackunderflow.com/tutorials/observability](https://thestackunderflow.com/tutorials/observability)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :09-observability:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

Set `logging.level.org.springframework.ai.chat.client.advisor=DEBUG` to see the logged calls; scrape `management` metrics for token/latency.

## Files
- [`Ep09Application.java`](src/main/java/com/thestackunderflow/springai/ep09/Ep09Application.java)
- [`ObservabilityDemo.java`](src/main/java/com/thestackunderflow/springai/ep09/ObservabilityDemo.java)
- [`application.properties`](src/main/resources/application.properties)
