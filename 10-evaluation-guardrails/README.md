# Episode 10 — Evaluation, Guardrails & Hallucination Control

Close the “it answered, but was it right?” gap with an evaluator — a second, independent model call that judges the first answer and returns pass/fail. Wire it into a JUnit test and it becomes a release gate.

📺 Video: https://youtu.be/tsYFTVahXeE · 📄 Tutorial: [thestackunderflow.com/tutorials/evaluation-guardrails](https://thestackunderflow.com/tutorials/evaluation-guardrails)

## Run it

```bash
export OPENAI_API_KEY=sk-...          # from the repo root
./gradlew :10-evaluation-guardrails:bootRun
```

Without a key the app still starts and prints the call shape instead of spending a token.

## Notes

This module shows the mechanism by hand. Spring AI ships it as the `Evaluator` interface with built-ins: `relevancyEvaluator.evaluate(new EvaluationRequest(question, docs, answer)).isPass()` — `RelevancyEvaluator` / `FactCheckingEvaluator`.

## Files
- [`Ep10Application.java`](src/main/java/com/thestackunderflow/springai/ep10/Ep10Application.java)
- [`EvaluationDemo.java`](src/main/java/com/thestackunderflow/springai/ep10/EvaluationDemo.java)
- [`application.properties`](src/main/resources/application.properties)
