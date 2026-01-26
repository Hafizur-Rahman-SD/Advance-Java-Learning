package bd.edu.seu.shopnopuribackend.modules.Scholarship.repo;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.Sch;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchSave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchSaveRepo extends JpaRepository<SchSave, Long> {
    Optional<SchSave> findByStuEmailAndSch_Id(String stuEmail, Long schId);
    List<SchSave> findByStuEmailOrderBySavedAtDesc(String stuEmail);
    long deleteByStuEmailAndSch_Id(String stuEmail, Long schId);
}
