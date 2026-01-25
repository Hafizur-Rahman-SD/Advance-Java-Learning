package bd.edu.seu.shopnopuribackend.modules.university.repository;

import bd.edu.seu.shopnopuribackend.modules.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByNameContainingIgnoreCase(String q);
}
