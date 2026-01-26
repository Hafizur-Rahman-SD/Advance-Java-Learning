package bd.edu.seu.shopnopuribackend.modules.career.repository;

import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerTestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CareerTestAttemptRepository extends JpaRepository<CareerTestAttempt, Long> {
    Optional<CareerTestAttempt> findTopByUserIdOrderBySubmittedAtDesc(Long userId);
}
