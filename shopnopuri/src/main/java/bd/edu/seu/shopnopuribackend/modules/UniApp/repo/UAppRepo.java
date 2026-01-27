package bd.edu.seu.shopnopuribackend.modules.UniApp.repo;

import bd.edu.seu.shopnopuribackend.modules.UniApp.entity.UApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UAppRepo extends JpaRepository<UApp, Long> {

    List<UApp> findByStuEmailOrderByUpdatedAtDesc(String stuEmail);

    Optional<UApp> findByIdAndStuEmail(Long id, String stuEmail);
}
