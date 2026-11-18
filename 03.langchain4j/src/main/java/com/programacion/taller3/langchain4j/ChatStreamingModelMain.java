package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

import static com.programacion.taller3.langchain4j.utils.EnvConfig.*;

class MyStreamingChatModel implements StreamingChatResponseHandler {

    private final AtomicInteger count;

    MyStreamingChatModel(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public void onPartialResponse(String partialResponse) {
        System.out.println(partialResponse);
        System.out.println("-----------------------------------");
    }

    @Override
    public void onCompleteResponse(ChatResponse completeResponse) {
        System.out.println();
        System.out.println(completeResponse);
        count.decrementAndGet();
    }

    @Override
    public void onError(Throwable error) {
        System.err.println("Error: " + error.getMessage());
        error.printStackTrace();
        count.decrementAndGet();
    }
}

public class ChatStreamingModelMain {

    static void main() throws InterruptedException {
        var chatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(KEY)
                .modelName(MODEL)
                .logRequests(true)
                .logResponses(true)
                .build();

        var count = new AtomicInteger(1);

        chatModel.chat("Que es langchain4j?", new MyStreamingChatModel(count));

        while (count.get() > 0) {
            Thread.sleep(100);
        }
    }
}
