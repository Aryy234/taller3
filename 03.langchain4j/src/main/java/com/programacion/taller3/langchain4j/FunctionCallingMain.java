package com.programacion.taller3.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.time.LocalDateTime;

import static com.programacion.taller3.langchain4j.utils.EnvConfig.*;

class MisHerramientas {
    @Tool("Obtiene la fecha actual")
    public String obtenerFechaHora(){
        return LocalDateTime.now().toString();
    }
}

interface MiAsistente {
    @SystemMessage("""
                    Eres un asistente util que utiliza las herramientas disponibles del usuario
                    """
            )
    String chat(String message);
}

public class FunctionCallingMain {

    static void main() {
        var chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var asistente = AiServices.builder(MiAsistente.class)
                .chatModel(chatModel)
                .tools(new MisHerramientas())
                .build();

        var respuesta = asistente.chat("Que hora es?");
        System.out.println(respuesta);
    }
}
