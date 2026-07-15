# Episode 12 — The Production-Style Enterprise Blueprint

The whole series on one ChatClient: a system prompt, an advisor stack (audit + RAG + memory), and `@Tool`s the model can call — composed like any Spring `@Service`.

📺 Video: https://youtu.be/QuvniDFJ__Q · 📄 Tutorial: [thestackunderflow.com/tutorials/production-blueprint](https://thestackunderflow.com/tutorials/production-blueprint)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :12-production-blueprint:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

See `SupportAssistant` for the assembled `ChatClient.builder(...).defaultSystem(...).defaultAdvisors(...).defaultTools(...).build()`. In production add `QuestionAnswerAdvisor` (RAG, Ep 6) and `MessageChatMemoryAdvisor` (memory) to the advisor stack.

## Files
- [`Ep12Application.java`](src/main/java/com/thestackunderflow/springai/ep12/Ep12Application.java)
- [`ProductionBlueprintDemo.java`](src/main/java/com/thestackunderflow/springai/ep12/ProductionBlueprintDemo.java)
- [`SupportAssistant.java`](src/main/java/com/thestackunderflow/springai/ep12/SupportAssistant.java)
- [`application.properties`](src/main/resources/application.properties)
