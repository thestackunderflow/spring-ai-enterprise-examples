// Root build for the "Spring AI for Enterprise Java" example modules.
// Baseline: Spring AI 2.0.0 GA on Spring Boot 4.0 (Spring Framework 7.0), Java 21.
// Each episode module is a small, self-contained Spring Boot app — content, not a framework.

plugins {
    java
    id("org.springframework.boot") version "4.0.0" apply false
}

// Pinned versions — confirm against start.spring.io when bumping (see RELEASE-WATCH in the content repo).
val springBootVersion = "4.0.0"
val springAiVersion = "2.0.0"

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")

    repositories { mavenCentral() }

    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
    }

    dependencies {
        // Boot + Spring AI BOMs manage every other version, so starters below carry no version.
        add("implementation", platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
        add("implementation", platform("org.springframework.ai:spring-ai-bom:$springAiVersion"))
        add("implementation", "org.springframework.boot:spring-boot-starter")
        add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<Test>().configureEach { useJUnitPlatform() }
}
