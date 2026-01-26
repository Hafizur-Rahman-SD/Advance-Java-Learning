package bd.edu.seu.shopnopuribackend.modules.career.repository;

import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerTestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerTestAnswerRepository extends JpaRepository<CareerTestAnswer, Long> {
}
