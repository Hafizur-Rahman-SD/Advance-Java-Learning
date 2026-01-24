package bd.edu.seu.aispringbootollama.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiChatService {

    private final ChatClient chatClient;

    public String generate(String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
