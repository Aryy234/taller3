package com.programacion.taller3.embeddings;

import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;

public class SimilitudMain {

    static void main() {
        var pathModel = Paths.get("/home/aryy/Documentos/Proyectos/taller3/modelos/model_O4.onnx");
        var pathTokenizar = Paths.get("/home/aryy/Documentos/Proyectos/taller3/modelos/tokenizer.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizar, PoolingMode.MEAN);

        var embedding1 = model.embed("Cual es el area de un circulo").content();
        var embedding2 = model.embed("Cual es el area de un cuadrado").content();
        var embedding3 = model.embed("Que hora es?").content();
        var embedding4 = model.embed("Cual es el area de un cuadrado").content();

        //calcular similitud de embeddings
        double similitud12 = CosineSimilarity.between(embedding1,embedding2);
        double similitud13 = CosineSimilarity.between(embedding1,embedding3);
        double similitud23 = CosineSimilarity.between(embedding2,embedding3);;
        double similitud24 = CosineSimilarity.between(embedding2,embedding3);;

        System.out.println("Similitud entre 1 y 2: " + similitud12);
        System.out.println("Similitud entre 1 y 3: " + similitud13);
        System.out.println("Similitud entre 3 y 2: " + similitud23);


    }
}
