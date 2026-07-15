# Episode 05 — Structured Output to Java POJOs

`.entity(...)` turns a model’s free-form text into a typed, validated Java object — Jackson-for-models — so nothing downstream ever handles raw model text.

📺 Video: [youtu.be/bYUHrNlorT4](https://youtu.be/bYUHrNlorT4) · 📄 Tutorial: [thestackunderflow.com/tutorials/structured-output](https://thestackunderflow.com/tutorials/structured-output)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :05-structured-output:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

`.entity(ActorFilms.class)` for one record; `.entity(new ParameterizedTypeReference<List<ActorFilms>>(){})` for a list (generics erasure). 2.0.0 adds `.entity(Class, spec -> spec.validateSchema())`, which retries a bad shape automatically.

## Files
- [`ActorFilms.java`](src/main/java/com/thestackunderflow/springai/ep05/ActorFilms.java)
- [`Ep05Application.java`](src/main/java/com/thestackunderflow/springai/ep05/Ep05Application.java)
- [`StructuredOutputDemo.java`](src/main/java/com/thestackunderflow/springai/ep05/StructuredOutputDemo.java)
- [`application.properties`](src/main/resources/application.properties)
