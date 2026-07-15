package com.thestackunderflow.springai.ep06;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The whole RAG loop in one class, in memory:
 * <ol>
 *   <li>Embed a few documents into a {@link SimpleVectorStore} (swap for PgVector/Redis in prod).</li>
 *   <li><b>Retrieve</b> the documents similar to the question, and add them to the prompt as context —
 *       so the answer is grounded in YOUR content, not the model's memory.</li>
 * </ol>
 *
 * <p>This shows the retrieval + augmentation by hand so the mechanism is explicit. In production you'd
 * collapse steps into one advisor:
 * <pre>
 *   chatClient.prompt()
 *       .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())  // retrieve + augment, automatically
 *       .user("...").call().content();
 * </pre>
 * (QuestionAnswerAdvisor lives in {@code org.springframework.ai.chat.client.advisor.vectorstore}.)
 */
@Component
public class RagDemo implements CommandLineRunner {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagDemo(ChatClient.Builder builder, EmbeddingModel embeddingModel) {
        this.chatClient = builder.build();
        // In-memory for the demo. In production: PgVectorStore / RedisVectorStore, same VectorStore API.
        this.vectorStore = SimpleVectorStore.builder(embeddingModel).build();
    }

    @Override
    public void run(String... args) {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) {
            System.out.println("""

                    [ep06] No OPENAI_API_KEY set — skipping (embedding + retrieval both need the model). Shape:
                           vectorStore.add(List.of(new Document("...")));         // embed your governed docs
                           List<Document> hits = vectorStore.similaritySearch(question);  // retrieve
                           chatClient.prompt().system("Answer using ONLY: " + context)... // augment + answer
                           // one-liner equivalent: .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                    """);
            return;
        }

        // 1. Embed your governed documents.
        vectorStore.add(List.of(
                new Document("Spring AI RAG retrieves documents similar to the user's question from a "
                        + "VectorStore and adds them to the prompt as context before the model answers."),
                new Document("The enterprise RAG rule: keep your source-of-truth documents in a governed "
                        + "VectorStore so answers cite content you control, not the model's training data."),
                new Document("SimpleVectorStore keeps embeddings in memory; PgVectorStore and RedisVectorStore "
                        + "persist them, exposing the identical VectorStore interface.")));

        String question = "Why keep documents in a governed vector store for RAG?";

        // 2. Retrieve the most relevant documents (this is the "R" in RAG).
        List<Document> hits = vectorStore.similaritySearch(question);
        String context = hits.stream().map(Document::getText).collect(Collectors.joining("\n- ", "- ", ""));

        // 3. Augment the prompt with the retrieved context, then answer — grounded.
        String answer = chatClient.prompt()
                .system("Answer the question using ONLY the following context:\n" + context)
                .user(question)
                .call()
                .content();
        System.out.println("\n[ep06] retrieved " + hits.size() + " docs; grounded answer -> " + answer + "\n");
    }
}
