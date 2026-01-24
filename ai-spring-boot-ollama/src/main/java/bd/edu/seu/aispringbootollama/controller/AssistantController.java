package bd.edu.seu.aispringbootollama.controller;


import bd.edu.seu.aispringbootollama.dto.AskRequest;
import bd.edu.seu.aispringbootollama.dto.ChatResponse;
import bd.edu.seu.aispringbootollama.service.AiChatService;
import bd.edu.seu.aispringbootollama.service.PromptLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
public class AssistantController {

    private final AiChatService aiChatService;
    private final PromptLogService promptLogService;

    @PostMapping("/ask")
    public ChatResponse ask(@RequestBody AskRequest request) {
        String question = request.getQuestion();
        String answer = aiChatService.generate(question);

        // anonymous logging (no user data)
        promptLogService.logAnonymous(question, answer);

        return new ChatResponse(answer);
    }
}

