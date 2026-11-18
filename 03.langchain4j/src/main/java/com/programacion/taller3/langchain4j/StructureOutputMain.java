package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import static com.programacion.taller3.langchain4j.utils.EnvConfig.*;

interface Extractor {
    @UserMessage("""
                Extrae la información de la siguiente entrada y devuelvela en formato JSON.
                Entrada: {{entrada}}
                """)
    String extraer(@V("entrada") String entrada);
}

public class StructureOutputMain {
    static void main() {
        var chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var extractor = AiServices.builder(Extractor.class)
                .chatModel(chatModel)
                .build();

        var texto = """
                Hola mi nombre es: Juan Pérez, tengo 30 años y soy ingeniero de software. 
                Me gusta programar en Java y Python, y en mi tiempo libre disfruto de la lectura y el senderismo.
                """;

        var persona = extractor.extraer(texto);

        System.out.println(persona);

    }
}
