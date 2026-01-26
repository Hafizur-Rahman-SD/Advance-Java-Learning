package bd.edu.seu.shopnopuribackend.modules.career.seed;

import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerDimension;
import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerQuestion;
import bd.edu.seu.shopnopuribackend.modules.career.repository.CareerQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CareerQuestionSeeder implements CommandLineRunner {

    private final CareerQuestionRepository repo;

    @Override
    public void run(String... args) {
        if (repo.count() > 0) return;

        List<CareerQuestion> questions = List.of(
                q("I enjoy solving complex problems step by step.", CareerDimension.ANALYTICAL),
                q("I like math/logic-based challenges.", CareerDimension.ANALYTICAL),

                q("I prefer accuracy and hate small mistakes.", CareerDimension.DETAIL),
                q("I can focus on a task for long periods.", CareerDimension.DETAIL),

                q("I enjoy creating new ideas or designs.", CareerDimension.CREATIVE),
                q("I like exploring multiple solutions instead of one fixed method.", CareerDimension.CREATIVE),

                q("I enjoy helping others understand things.", CareerDimension.SOCIAL),
                q("I feel energized when working with people.", CareerDimension.SOCIAL),

                q("I like taking responsibility and leading a group.", CareerDimension.LEADERSHIP),
                q("I can make decisions even under pressure.", CareerDimension.LEADERSHIP),

                q("I enjoy learning new tools/skills regularly.", CareerDimension.ANALYTICAL),
                q("I prefer structured plans over randomness.", CareerDimension.DETAIL),
                q("I handle stress well during exams/deadlines.", CareerDimension.LEADERSHIP),
                q("I like explaining my ideas clearly.", CareerDimension.SOCIAL),
                q("I enjoy building things (projects) more than only reading theory.", CareerDimension.CREATIVE)
        );

        repo.saveAll(questions);
    }

    private CareerQuestion q(String text, CareerDimension d) {
        return CareerQuestion.builder()
                .questionText(text)
                .dimension(d)
                .active(true)
                .build();
    }
}
