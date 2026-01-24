package bd.edu.seu.aispringbootollama.controller;


import bd.edu.seu.aispringbootollama.dto.ChatRequest;
import bd.edu.seu.aispringbootollama.dto.ChatResponse;
import bd.edu.seu.aispringbootollama.dto.PersonalizedRequest;
import bd.edu.seu.aispringbootollama.service.AiChatService;
import bd.edu.seu.aispringbootollama.service.PromptTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiChatService aiChatService;
    private final PromptTemplateService promptTemplateService;

    // Task 1: simple prompt for  AI response
    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String answer = aiChatService.generate(request.getPrompt());
        return new ChatResponse(answer);
    }

    // Task 2: template prompt for  personalized response
    @PostMapping("/personalized")
    public ChatResponse personalized(@RequestBody PersonalizedRequest request) {
        String prompt = promptTemplateService.buildPersonalizedPrompt(
                request.getName(), request.getTopic()
        );
        String answer = aiChatService.generate(prompt);
        return new ChatResponse(answer);
    }
}
