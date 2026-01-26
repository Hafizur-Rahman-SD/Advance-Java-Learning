package bd.edu.seu.shopnopuribackend.modules.Scholarship.repo;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.Sch;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchAppRepo extends JpaRepository<SchApp, Long> {
    Optional<SchApp> findByStuEmailAndSch_Id(String stuEmail, Long schId);
    List<SchApp> findByStuEmailOrderByAppliedAtDesc(String stuEmail);
}
