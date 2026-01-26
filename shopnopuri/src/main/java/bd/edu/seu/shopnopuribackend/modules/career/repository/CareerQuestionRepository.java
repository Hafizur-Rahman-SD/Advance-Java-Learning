package bd.edu.seu.shopnopuribackend.modules.career.repository;

import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerQuestionRepository extends JpaRepository<CareerQuestion, Long> {
    List<CareerQuestion> findByActiveTrueOrderByIdAsc();
}
