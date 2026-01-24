package bd.edu.seu.aispringbootollama.service;



import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

@Service
public class PromptLogService {

    private static final Path LOG_FILE = Path.of("prompt_logs.txt");

    public void logAnonymous(String prompt, String response) {
        try {
            String record = "time=" + Instant.now()
                    + "\nprompt=" + prompt
                    + "\nresponse=" + response
                    + "\n-----------------------------\n";
            Files.writeString(LOG_FILE, record,
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND);
        } catch (Exception ignored) {
            // keep it silent so API doesn't fail due to logging
        }
    }
}
