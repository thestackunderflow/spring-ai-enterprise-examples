// Episode 4 — "Model Portability" — one ChatClient, swappable ChatModel beans.
// Both starters on the classpath; `spring.ai.model.chat` selects which ChatModel is wired.
dependencies {
    implementation("org.springframework.ai:spring-ai-starter-model-openai")
    implementation("org.springframework.ai:spring-ai-starter-model-ollama")
}
