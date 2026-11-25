package com.programacion.taller3.springPrompt.rest;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    final ChatClient chatClient;

    @Value("classpath:/prompts/system-prompt.st")
    Resource systemPrompt;

    @Value("classpath:/prompts/user-prompt.st")
    Resource userPrompt;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                //LOG request/response del chat
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestBody ChatRequest request){
        return chatClient.prompt()
                .system(promptSystemSpec -> promptSystemSpec
                        .text(systemPrompt))
                .user(promptUserSpec -> promptUserSpec
                        .text(userPrompt)
                        .param("pregunta", request.message()))
                .call()
                .content();
    }
}
