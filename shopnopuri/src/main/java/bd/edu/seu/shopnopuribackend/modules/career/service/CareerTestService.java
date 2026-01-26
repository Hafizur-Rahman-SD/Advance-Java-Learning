package bd.edu.seu.shopnopuribackend.modules.career.service;

import bd.edu.seu.shopnopuribackend.modules.career.dto.*;
import bd.edu.seu.shopnopuribackend.modules.career.entity.*;
import bd.edu.seu.shopnopuribackend.modules.career.repository.*;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CareerTestService {

    private final CareerQuestionRepository questionRepository;
    private final CareerTestAttemptRepository attemptRepository;
    private final CareerTestAnswerRepository answerRepository;
    private final UserRepository userRepository;

    private final CareerScoringEngine scoringEngine = new CareerScoringEngine();

    public List<CareerQuestionResponse> getActiveQuestions() {
        return questionRepository.findByActiveTrueOrderByIdAsc().stream()
                .map(q -> CareerQuestionResponse.builder()
                        .id(q.getId())
                        .questionText(q.getQuestionText())
                        .dimension(q.getDimension())
                        .build())
                .toList();
    }

    public CareerResultResponse submit(String email, CareerSubmitRequest req) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Load active questions into a map for validation
        List<CareerQuestion> activeQuestions = questionRepository.findByActiveTrueOrderByIdAsc();
        Map<Long, CareerQuestion> questionMap = new HashMap<>();
        for (CareerQuestion q : activeQuestions) questionMap.put(q.getId(), q);

        if (req.getAnswers().size() < Math.min(10, activeQuestions.size())) {
            throw new IllegalArgumentException("Please answer at least 10 questions.");
        }

        // Aggregate dimension scores
        Map<CareerDimension, Integer> dimensionScore = new EnumMap<>(CareerDimension.class);
        for (CareerDimension d : CareerDimension.values()) dimensionScore.put(d, 0);

        // Create attempt first
        CareerTestAttempt attempt = CareerTestAttempt.builder()
                .user(user)
                .resultJson("{}")
                .build();
        attempt = attemptRepository.save(attempt);

        // Save answers
        for (CareerSubmitRequest.Answer a : req.getAnswers()) {
            CareerQuestion q = questionMap.get(a.getQuestionId());
            if (q == null) {
                throw new IllegalArgumentException("Invalid or inactive questionId: " + a.getQuestionId());
            }
            int value = a.getValue(); // 0..4
            dimensionScore.put(q.getDimension(), dimensionScore.get(q.getDimension()) + value);

            CareerTestAnswer ans = CareerTestAnswer.builder()
                    .attempt(attempt)
                    .question(q)
                    .value(value)
                    .build();
            answerRepository.save(ans);
        }

        // Calculate result
        CareerResultResponse result = scoringEngine.score(dimensionScore);

        // Store as JSON-like string (simple V1). Later you can use Jackson to store real JSON.
        String resultJson = buildResultJson(result);
        attempt.setResultJson(resultJson);
        attemptRepository.save(attempt);

        return result;
    }

    public CareerResultResponse getLatestResult(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        CareerTestAttempt attempt = attemptRepository.findTopByUserIdOrderBySubmittedAtDesc(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("No career test result found"));

        // V1: We will not parse stored JSON (keeps it simple).
        // Instead, return a minimal response with stored string.
        return CareerResultResponse.builder()
                .personalitySummary("Saved result exists")
                .dimensionScore(Map.of())
                .topCareers(List.of(
                        CareerResultResponse.CareerMatch.builder()
                                .career("See result_json in DB")
                                .matchPercent(0)
                                .build()
                ))
                .suggestions(List.of("Result saved in DB (career_test_attempts.result_json)."))
                .build();
    }

    private String buildResultJson(CareerResultResponse r) {
        // Simple V1 string; later replace with ObjectMapper for real JSON
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"personality\":\"").append(r.getPersonalitySummary()).append("\",");
        sb.append("\"topCareers\":[");
        for (int i = 0; i < r.getTopCareers().size(); i++) {
            var c = r.getTopCareers().get(i);
            sb.append("{\"career\":\"").append(c.getCareer()).append("\",\"match\":").append(c.getMatchPercent()).append("}");
            if (i < r.getTopCareers().size() - 1) sb.append(",");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
