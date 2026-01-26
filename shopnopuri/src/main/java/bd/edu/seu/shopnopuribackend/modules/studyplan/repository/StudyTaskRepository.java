package bd.edu.seu.shopnopuribackend.modules.studyplan.repository;

import bd.edu.seu.shopnopuribackend.modules.studyplan.entity.StudyTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTaskRepository extends JpaRepository<StudyTask, Long> {
}
