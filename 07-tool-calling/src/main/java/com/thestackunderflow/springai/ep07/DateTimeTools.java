package com.thestackunderflow.springai.ep07;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;

/**
 * Plain Java methods + {@code @Tool}. That annotation is the entire contract — the {@code description} is
 * what the model reads to decide when to call it. Arguments come back as JSON and bind onto the method
 * signature; you never parse them yourself.
 */
public class DateTimeTools {

    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }

    @Tool(description = "Set a user alarm for the given time, provided in ISO-8601 format")
    String setAlarm(String time) {
        // A real service would persist the alarm; here we just confirm the bound argument round-tripped.
        return "Alarm set for " + LocalDateTime.parse(time);
    }
}
