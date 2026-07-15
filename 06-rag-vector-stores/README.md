# Episode 06 — RAG with Spring AI

Embed your governed documents into a VectorStore, retrieve the relevant ones, and add them to the prompt — so the model answers from content you control, grounded.

📺 Video: https://youtu.be/RE8LizkOldE · 📄 Tutorial: [thestackunderflow.com/tutorials/rag-vector-stores](https://thestackunderflow.com/tutorials/rag-vector-stores)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :06-rag-vector-stores:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

This module shows the loop by hand (`vectorStore.similaritySearch(...)` + manual context). The production one-liner is `.advisors(QuestionAnswerAdvisor.builder(vectorStore).build())` (package `org.springframework.ai.chat.client.advisor.vectorstore`). Swap `SimpleVectorStore` for `PgVectorStore`/`RedisVectorStore` — same `VectorStore` API.

## Files
- [`Ep06Application.java`](src/main/java/com/thestackunderflow/springai/ep06/Ep06Application.java)
- [`RagDemo.java`](src/main/java/com/thestackunderflow/springai/ep06/RagDemo.java)
- [`application.properties`](src/main/resources/application.properties)
