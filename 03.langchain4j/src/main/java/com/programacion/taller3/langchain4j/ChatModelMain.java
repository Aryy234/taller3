package com.programacion.taller3.langchain4j;

import com.programacion.taller3.langchain4j.utils.AsistenteProgramacion;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static com.programacion.taller3.langchain4j.utils.EnvConfig.*;

public class ChatModelMain {

    static void main() {

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var asistente = AiServices.create(AsistenteProgramacion.class, chatModel);

        var respuesta = chatModel.chat("Hola, ¿cómo estás?");

        System.out.println(respuesta);

        System.out.println("-----------------------------------");
        var contexto = """
                Requisitos de titulacion
                1. El estudiante debe haber aprobado todas las asignaturas del plan de estudios.
                2. El estudiante debe haber cumplido con el requisito de prácticas profesionales.
                3. El estudiante debe haber presentado y aprobado el trabajo de investigación o proyecto final.
                4. El estudiante debe haber cumplido con los requisitos de asistencia y participación en clase
                """;

        var respuesta2 = asistente.consultar("¿Cuáles son los requisitos de titulación?", contexto);
        System.out.println(respuesta2);


    }
}
