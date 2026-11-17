package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatModelMain {

    final static String BASE_URL = "http://localhost:8080";
    final static String KEY = System.getenv().getOrDefault("GROQ_API_KEY", "TU_API_KEY");
    final static String MODEL = "gpt-3.5-turbo";

    static void main() {

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var respuesta = chatModel.chat("Hola, ¿cómo estás?");

        System.out.println(respuesta);

    }
}
