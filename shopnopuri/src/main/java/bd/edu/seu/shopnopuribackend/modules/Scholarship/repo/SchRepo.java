package bd.edu.seu.shopnopuribackend.modules.Scholarship.repo;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.Sch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SchRepo extends JpaRepository<Sch, Long> {

    Page<Sch> findByActTrue(Pageable pageable);

    Page<Sch> findByActTrueAndCountryIgnoreCase(
            String country,
            Pageable pageable
    );

    @Query("""
SELECT s FROM Sch s
WHERE s.act = true
  AND (:category IS NULL OR LOWER(s.category) = LOWER(:category))
AND (:district IS NULL OR s.district IS NULL OR LOWER(s.district) LIKE LOWER(CONCAT('%', :district, '%')))
  AND (:university IS NULL OR s.university IS NULL OR LOWER(s.university) LIKE LOWER(CONCAT('%', :university, '%')))
  AND (:gender IS NULL OR s.gender IS NULL OR LOWER(s.gender) = LOWER(:gender))
  AND (:gpa IS NULL OR s.minGpa IS NULL OR :gpa >= s.minGpa)
  AND (:gpa IS NULL OR s.maxGpa IS NULL OR :gpa <= s.maxGpa)
  AND (:income IS NULL OR s.maxFamilyIncomeBdt IS NULL OR :income <= s.maxFamilyIncomeBdt)
""")
    Page<Sch> search(
            @Param("category") String category,
            @Param("district") String district,
            @Param("university") String university,
            @Param("gender") String gender,
            @Param("gpa") Double gpa,
            @Param("income") Integer income,
            Pageable pageable
    );



}
