package bd.edu.seu.aispringbootollama.service;


import org.springframework.stereotype.Service;

@Service
public class PromptTemplateService {

    public String buildPersonalizedPrompt(String name, String topic) {
        return String.format(
                "Hi %s! Explain '%s' in simple English with a real-life example in 6-8 lines.",
                name, topic
        );
    }
}
