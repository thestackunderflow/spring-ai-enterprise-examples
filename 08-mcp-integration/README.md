# Episode 08 — MCP with Spring AI

The same `@Tool` methods from Episode 7, exposed over the standard **Model Context Protocol** so any MCP client (Claude Desktop, another service) can call them.

📺 Video: [youtu.be/gOHcj39zEPM](https://youtu.be/gOHcj39zEPM) · 📄 Tutorial: [thestackunderflow.com/tutorials/mcp-integration](https://thestackunderflow.com/tutorials/mcp-integration)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :08-mcp-integration:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

**Server**: add `spring-ai-starter-mcp-server-webmvc`, set `spring.ai.mcp.server.stdio=true` (or a `protocol`) — your `@Tool` beans are auto-discovered as MCP tools. **Client**: add `spring-ai-starter-mcp-client`, then `ChatClient.builder(model).defaultTools(toolCallbackProvider).build()` (2.0.0; `defaultToolCallbacks(...)` is deprecated).

## Files
- [`CalculatorService.java`](src/main/java/com/thestackunderflow/springai/ep08/CalculatorService.java)
- [`Ep08Application.java`](src/main/java/com/thestackunderflow/springai/ep08/Ep08Application.java)
- [`McpToolsDemo.java`](src/main/java/com/thestackunderflow/springai/ep08/McpToolsDemo.java)
- [`application.properties`](src/main/resources/application.properties)
