package com.programacion.taller3.langchain4j;

import com.programacion.taller3.langchain4j.utils.AsistenteProgramacion;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static com.programacion.taller3.langchain4j.utils.EnvConfig.*;

public class AiServiceMain {

    static void main() {

        var chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var asistente = AiServices.create(AsistenteProgramacion.class, chatModel);

        var respuesta = asistente.responderPregunta("¿Cuál es la diferencia entre un contrato y un acuerdo?");
        System.out.println(respuesta);

    }

}
