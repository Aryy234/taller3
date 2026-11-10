package com.programacion.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;
import java.util.List;

public class BusquedaSemanticaMain {
    static void main() {

        var pathModel = Paths.get("/home/aryy/Documentos/Proyectos/taller3/modelos/model_O4.onnx");
        var pathTokenizar = Paths.get("/home/aryy/Documentos/Proyectos/taller3/modelos/tokenizer.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizar, PoolingMode.MEAN);

        var documentos = List.of(
                "Java es un lenguaje orientado a objetos",
                "Python es un lenguaje orientado a inteligencia artificial",
                "Los embeddings representan vecotres"
        );

        List<Embedding> embeddings = documentos.stream()
                .map(model::embed)
                .map(Response::content)
                .toList();

        var consulta = "programacion orientada a objetos";
        var embeddingConsulta = model.embed(consulta).content();

        int index = 0;
        int mejorIndex = 0;
        double mejorScore = -1.0;

        for(var it:embeddings){
            double score = CosineSimilarity.between(embeddingConsulta, it);

            System.out.printf("Documento %d - Similitud: %.4f%n", index, score);

            if(score > mejorScore){
                mejorScore = score;
                mejorIndex = index;
            }
            index++;

        }

        System.out.println("El documento más similar a la consulta es: " + documentos.get(mejorIndex));
        System.out.println("Con un score de similitud de: " + mejorScore);
    }
}
