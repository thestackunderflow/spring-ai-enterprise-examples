package com.thestackunderflow.springai.ep08;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

/**
 * A plain {@code @Service} with {@code @Tool} methods — identical to Episode 7's contract.
 *
 * <p>With {@code spring-ai-starter-mcp-server} on the classpath and {@code spring.ai.mcp.server.stdio=true}
 * (or a {@code protocol}), Spring AI <b>auto-discovers these @Tool beans</b> and publishes them as an MCP
 * server's tool list — zero extra registration code. The same methods are then callable by any MCP client.
 */
@Service
public class CalculatorService {

    @Tool(description = "Add two numbers")
    double add(double a, double b) {
        return a + b;
    }

    @Tool(description = "Multiply two numbers")
    double multiply(double a, double b) {
        return a * b;
    }

    @Tool(description = "Square root of a number")
    double sqrt(double value) {
        return Math.sqrt(value);
    }
}
