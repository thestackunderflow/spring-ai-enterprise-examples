package com.thestackunderflow.springai.ep11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 11 — "Enterprise AI Without Losing Control: Security & Compliance".
 *
 * The four controls, and where each lives in a Spring AI 2.0.0 app:
 * <ol>
 *   <li><b>Secrets</b> — {@code spring.ai.openai.api-key=${OPENAI_API_KEY}} (externalized; never in code).</li>
 *   <li><b>Authz</b> — Spring Security (@PreAuthorize) around the app + a least-privilege check inside each
 *       {@code @Tool} method. (Your architecture; Spring AI doesn't ship it.)</li>
 *   <li><b>Data boundary</b> — a {@code SafeGuardAdvisor} in the advisor chain blocks sensitive input before
 *       it reaches the model; a {@code ModerationModel} flags PII.</li>
 *   <li><b>Audit</b> — a {@code SimpleLoggerAdvisor} + Micrometer observation record who/what/when/tokens.</li>
 * </ol>
 * This module demonstrates controls 1 and 4 in code (see {@link SecurityControlsDemo}); the rest are
 * documented in the README, being architecture patterns rather than a single Spring AI class.
 */
@SpringBootApplication
public class Ep11Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep11Application.class, args);
    }
}
