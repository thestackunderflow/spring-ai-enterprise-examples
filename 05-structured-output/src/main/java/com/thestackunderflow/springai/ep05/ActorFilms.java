package com.thestackunderflow.springai.ep05;

import java.util.List;

/**
 * A plain Java record. Spring AI builds a JSON schema from its shape, hands that to the model, and
 * deserializes the response straight back into an instance — you never touch raw JSON or model text.
 */
public record ActorFilms(String actor, List<String> movies) {
}
