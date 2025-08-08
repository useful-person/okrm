// package com.useful_person.ai.component;

// import java.util.Scanner;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.useful_person.ai.services.AIService;

// @Component
// public class ChatClient implements CommandLineRunner {

// private final AIService aiService;

// public ChatClient(AIService aiService) {
// this.aiService = aiService;
// }

// @Override
// public void run(String... args) throws Exception {

// Scanner scanner = new Scanner(System.in);
// System.out.println("Spring AI Chat Client - Type 'exit' to stop");

// while (true) {
// System.out.print("You: ");
// String prompt = scanner.nextLine();

// if ("exit".equalsIgnoreCase(prompt)) {
// System.out.println("Exiting...");
// break;
// }

// String response = aiService.getResponse(prompt);
// System.out.println("AI: " + response);
// }

// scanner.close();
// }

// }
