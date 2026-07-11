# Episode 03 — Spring AI ChatClient, Explained

The fluent chain `prompt → system/user → call → content/chatResponse`, and the piece the quick-starts skip:
the response carries the model name and token usage — your cost meter, for free.

📺 Video: _(link on publish)_ · 📄 Tutorial: [thestackunderflow.com/tutorials/chatclient-explained](https://thestackunderflow.com/tutorials/chatclient-explained)

## Run it

```bash
export OPENAI_API_KEY=sk-...
./gradlew :03-chatclient-explained:bootRun
```

## The whole chain — and the receipt

```java
ChatResponse response = chatClient.prompt()
        .system("You are concise. Answer in two sentences.")
        .user("Why is the sky blue?")
        .call()
        .chatResponse();               // the whole envelope, not just .content()

String answer = response.getResult().getOutput().getText();
Usage usage   = response.getMetadata().getUsage();

response.getMetadata().getModel();     // which model answered
usage.getPromptTokens();               // what you sent
usage.getCompletionTokens();           // what it generated
usage.getTotalTokens();                // what you pay for
```

**The rule:** if cost or governance matters, reach for `.chatResponse()`, not `.content()` — the metadata
is already there. Wire `Usage` into a metric and you have per-call cost tracking before shipping a feature.
