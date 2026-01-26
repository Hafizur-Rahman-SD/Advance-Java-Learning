package bd.edu.seu.shopnopuribackend.modules.studyplan.repository;

import bd.edu.seu.shopnopuribackend.modules.studyplan.entity.StudyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
    List<StudyPlan> findByStudentEmailOrderByCreatedAtDesc(String studentEmail);
}
