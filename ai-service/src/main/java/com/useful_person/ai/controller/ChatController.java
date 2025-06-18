package com.useful_person.ai.controller;

import org.springframework.web.bind.annotation.RestController;
import com.useful_person.ai.services.AIService;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class ChatRequestBody {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

@RestController
public class ChatController {

    @Resource
    private AIService aiService;

    @PostMapping("/ai/deepseek/chat")
    public String chat(@RequestBody ChatRequestBody entity) {
        return aiService.getResponse(entity.getMessage());
    }

}
