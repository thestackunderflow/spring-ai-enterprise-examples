# Episode 07 — Tool Calling in Spring AI

Annotate a plain Java method with `@Tool`, hand the object to the ChatClient, and the model can decide to call it — Spring AI binds the JSON arguments straight onto your method signature.

📺 Video: [youtu.be/yN6fPGRL6_0](https://youtu.be/yN6fPGRL6_0) · 📄 Tutorial: [thestackunderflow.com/tutorials/tool-calling](https://thestackunderflow.com/tutorials/tool-calling)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :07-tool-calling:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

The model chooses when to call; it’s your Java that runs, so you stay in control.

## Files
- [`DateTimeTools.java`](src/main/java/com/thestackunderflow/springai/ep07/DateTimeTools.java)
- [`Ep07Application.java`](src/main/java/com/thestackunderflow/springai/ep07/Ep07Application.java)
- [`ToolCallingDemo.java`](src/main/java/com/thestackunderflow/springai/ep07/ToolCallingDemo.java)
- [`application.properties`](src/main/resources/application.properties)
