# Spring AI for Enterprise Java — Examples

Small, runnable **Spring AI 2.0.0** examples for enterprise Java / Spring Boot teams — one module per
episode of the [**Spring AI for Enterprise Java**](https://www.youtube.com/@TheStackUnderflow) video series.

Each module is a self-contained Spring Boot app you can run on its own. This is a **learning companion, not a
framework** — no shared abstractions, no magic. Read the code, run it, adapt it.

- **Watch:** [@TheStackUnderflow](https://www.youtube.com/@TheStackUnderflow?sub_confirmation=1)
- **Read:** [thestackunderflow.com/tutorials](https://thestackunderflow.com/tutorials)

## Requirements

| Tool | Version | Why |
|------|---------|-----|
| **JDK** | **21+** | Spring AI 2.0.0 → Spring Boot 4.0 → Spring Framework 7.0 requires Java 21 to build. |
| Gradle | (wrapper included) | `./gradlew` — no local Gradle install needed. |
| API key | OpenAI (and/or Anthropic) | Set via env — see [`.env.example`](.env.example). |

## Quick start

```bash
git clone https://github.com/thestackunderflow/spring-ai-enterprise-examples.git
cd spring-ai-enterprise-examples

cp .env.example .env          # then put your real key in .env (git-ignored)
export OPENAI_API_KEY=sk-...   # or load .env however you like

# Run one episode's example:
./gradlew :01-what-is-spring-ai:bootRun
```

> **Never commit a real key.** `.env` is git-ignored; the examples read keys from environment variables
> only. `application.properties` uses `${OPENAI_API_KEY:}` so the app starts (for tests) even without one.

## Modules

| # | Module | Concept | Video |
|---|--------|---------|-------|
| 01 | [`01-what-is-spring-ai`](01-what-is-spring-ai) | ChatClient in ~10 lines; the enterprise seam | _(link on publish)_ |
| 02 | `02-why-spring-boot-teams` | DI, starters, auto-config — the patterns that transfer | _soon_ |
| 03 | `03-chatclient-explained` | The fluent chain + reading token usage / metadata | _soon_ |
| 04 | `04-model-portability` | One API, swappable providers (OpenAI · Anthropic · Ollama) | _soon_ |
| 05 | `05-structured-output` | `.entity()` — AI text → typed Java records | _soon_ |
| 06 | `06-rag-vector-stores` | RAG: docs → embeddings → vector store → grounded answer | _soon_ |
| 07 | `07-tool-calling` | `@Tool` — the model calls your Java services | _soon_ |
| 08 | `08-mcp-integration` | MCP client + server (the standard tool port) | _soon_ |
| 09 | `09-observability` | Traces, metrics, token/latency for every model call | _soon_ |
| 10 | `10-evaluation-guardrails` | `RelevancyEvaluator` — test AI output like code | _soon_ |
| 11 | `11-security-compliance` | Secrets, boundaries, `SafeGuardAdvisor`, audit | _soon_ |
| 12 | `12-production-blueprint` | Everything wired together, end to end | _soon_ |

Modules land as each episode ships; folders for upcoming ones hold a short README until then.

## Versioning

Pinned to **Spring AI 2.0.0** (see [`build.gradle.kts`](build.gradle.kts)). When Spring AI releases a new
version, examples are updated alongside a "What changed" video — [subscribe](https://www.youtube.com/@TheStackUnderflow?sub_confirmation=1)
to catch those.

## License

[Apache-2.0](LICENSE). Examples are original, written against the Spring AI 2.0.0 reference docs; the
concepts are inspired by Ken Kousen's Apache-2.0 Spring AI Training Course.
