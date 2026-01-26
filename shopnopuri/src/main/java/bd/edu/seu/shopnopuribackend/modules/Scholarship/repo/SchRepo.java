package bd.edu.seu.shopnopuribackend.modules.Scholarship.repo;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.Sch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchRepo extends JpaRepository<Sch, Long> {

    Page<Sch> findByActTrue(Pageable pageable);

    Page<Sch> findByActTrueAndCountryIgnoreCase(
            String country,
            Pageable pageable
    );
}
