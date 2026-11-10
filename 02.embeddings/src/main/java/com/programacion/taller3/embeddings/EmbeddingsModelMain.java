package com.programacion.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

import java.nio.file.Paths;

public class EmbeddingsModelMain {
    static void main() {
        var pathModel = Paths.get("/home/aryy/Documentos/Proyectos/taller3/modelos/model_O4.onnx");
        var pathTokenizar = Paths.get("/home/aryy/Documentos/Proyectos/taller3/modelos/tokenizer.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizar, PoolingMode.MEAN);

        var text = "Hola, como estas?";


        Response<Embedding> response =  model.embed(text);
        Embedding embedding = response.content();

        System.out.println("Dimension of embedding: " + embedding.dimension());
        System.out.println("Embedding vector: " + embedding.vectorAsList());
    }
}
