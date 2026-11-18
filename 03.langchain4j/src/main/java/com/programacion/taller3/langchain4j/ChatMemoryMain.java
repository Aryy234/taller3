package com.programacion.taller3.langchain4j;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;

import java.util.Scanner;

import static com.programacion.taller3.langchain4j.utils.EnvConfig.*;

@SystemMessage("""
Responde con parrafos cortos y claros, si no sabes la respuesta, di 'No sé'.
""")
interface MiChat {
    Result<String> chat(String mensaje);
}

public class ChatMemoryMain {
    static void main() {
        var chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        ChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        var chat = AiServices.builder(MiChat.class)
                .chatModel(chatModel)
                .chatMemory(memory)
                .build();

        Scanner scanner = new Scanner(System.in);
        int totalTokens = 0;

        while (true) {
            System.out.println("mensaje>: ");
            String mensaje = scanner.nextLine();

            if (mensaje.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del chat... Total de tokens consumidos: " + totalTokens);
                break;
            }

            Result<String> result = chat.chat(mensaje);
            int tokensRespuesta = result.tokenUsage().totalTokenCount();
            totalTokens += tokensRespuesta;

            System.out.println("respuesta>: " + result.content());
            System.out.println("[Tokens en esta petición: " + tokensRespuesta + " | Total acumulado: " + totalTokens + "]");
        }
    }
}
