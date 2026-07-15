package com.thestackunderflow.springai.ep08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Episode 8 — "MCP with Spring AI: Connect AI to Tools the Standard Way".
 *
 * The point: the {@code @Tool} methods you wrote in Episode 7 don't have to stay inside one app. As an MCP
 * <b>server</b>, Spring AI auto-discovers your {@code @Tool} beans and exposes them over a standard
 * transport (STDIO / SSE / Streamable) so any MCP client — Claude Desktop, another service — can call
 * them. As an MCP <b>client</b>, you point at an external server and its tools become {@code ToolCallback}s
 * on your ChatClient. See the README for the {@code spring-ai-starter-mcp-server}/{@code -client} config.
 */
@SpringBootApplication
public class Ep08Application {
    public static void main(String[] args) {
        SpringApplication.run(Ep08Application.class, args);
    }
}
