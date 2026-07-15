# Episode 11 — Security & Compliance

The four controls and where each lives in a Spring AI app: **Secrets** (externalized key), **Authz** (Spring Security + in-`@Tool` checks), **Data boundary** (`SafeGuardAdvisor` + `ModerationModel`), **Audit** (`SimpleLoggerAdvisor` + Micrometer).

📺 Video: [youtu.be/m6KhNUVk2fk](https://youtu.be/m6KhNUVk2fk) · 📄 Tutorial: [thestackunderflow.com/tutorials/security-compliance](https://thestackunderflow.com/tutorials/security-compliance)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :11-security-compliance:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

This module demonstrates Secrets (config) + Audit (`SimpleLoggerAdvisor`) in code. Data-boundary chains a `SafeGuardAdvisor` + `ModerationModel` the same way; Authz is Spring Security + a least-privilege check inside each `@Tool` (your architecture, not a Spring AI API).

## Files
- [`Ep11Application.java`](src/main/java/com/thestackunderflow/springai/ep11/Ep11Application.java)
- [`SecurityControlsDemo.java`](src/main/java/com/thestackunderflow/springai/ep11/SecurityControlsDemo.java)
- [`application.properties`](src/main/resources/application.properties)
