package bd.edu.seu.shopnopuribackend.modules.university.service;

import bd.edu.seu.shopnopuribackend.modules.university.dto.DepartmentResponse;
import bd.edu.seu.shopnopuribackend.modules.university.dto.UniversityResponse;
import bd.edu.seu.shopnopuribackend.modules.university.entity.University;
import bd.edu.seu.shopnopuribackend.modules.university.repository.DepartmentRepository;
import bd.edu.seu.shopnopuribackend.modules.university.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import bd.edu.seu.shopnopuribackend.modules.university.dto.CreateDepartmentRequest;
import bd.edu.seu.shopnopuribackend.modules.university.dto.CreateUniversityRequest;
import bd.edu.seu.shopnopuribackend.modules.university.entity.Department;
import bd.edu.seu.shopnopuribackend.modules.university.entity.University;


import java.util.List;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final DepartmentRepository departmentRepository;

    public UniversityService(UniversityRepository universityRepository,
                             DepartmentRepository departmentRepository) {
        this.universityRepository = universityRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<UniversityResponse> getAllUniversities(String q) {
        List<University> universities =
                (q == null || q.isBlank())
                        ? universityRepository.findAll()
                        : universityRepository.findByNameContainingIgnoreCase(q);

        return universities.stream()
                .map(u -> new UniversityResponse(
                        u.getId(),
                        u.getName(),
                        u.getType(),
                        u.getCity(),
                        u.getWebsite(),
                        u.getDescription()
                ))
                .toList();
    }

    public UniversityResponse getUniversityById(Long id) {
        University u = universityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("University not found"));

        return new UniversityResponse(
                u.getId(),
                u.getName(),
                u.getType(),
                u.getCity(),
                u.getWebsite(),
                u.getDescription()
        );
    }

    public List<DepartmentResponse> getDepartmentsByUniversity(Long universityId) {
        return departmentRepository.findByUniversityId(universityId)
                .stream()
                .map(d -> new DepartmentResponse(
                        d.getId(),
                        d.getName(),
                        d.getCode(),
                        d.getGroupName(),
                        d.getAdmissionRequirements()
                ))
                .toList();
    }

    public UniversityResponse createUniversity(CreateUniversityRequest req) {
        University u = new University();
        u.setName(req.getName());
        u.setType(req.getType());
        u.setCity(req.getCity());
        u.setWebsite(req.getWebsite());
        u.setDescription(req.getDescription());

        University saved = universityRepository.save(u);

        return new UniversityResponse(
                saved.getId(),
                saved.getName(),
                saved.getType(),
                saved.getCity(),
                saved.getWebsite(),
                saved.getDescription()
        );
    }

    public DepartmentResponse createDepartment(CreateDepartmentRequest req) {
        University uni = universityRepository.findById(req.getUniversityId())
                .orElseThrow(() -> new IllegalArgumentException("University not found"));

        Department d = new Department();
        d.setUniversity(uni);
        d.setName(req.getName());
        d.setCode(req.getCode());
        d.setGroupName(req.getGroupName());
        d.setAdmissionRequirements(req.getAdmissionRequirements());

        Department saved = departmentRepository.save(d);

        return new DepartmentResponse(
                saved.getId(),
                saved.getName(),
                saved.getCode(),
                saved.getGroupName(),
                saved.getAdmissionRequirements()
        );
    }





    public UniversityResponse updateUniversity(Long id, CreateUniversityRequest req) {
        University u = universityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("University not found"));

        u.setName(req.getName());
        u.setType(req.getType());
        u.setCity(req.getCity());
        u.setWebsite(req.getWebsite());
        u.setDescription(req.getDescription());

        University saved = universityRepository.save(u);

        return new UniversityResponse(
                saved.getId(),
                saved.getName(),
                saved.getType(),
                saved.getCity(),
                saved.getWebsite(),
                saved.getDescription()
        );
    }

    //for university delate
    public void deleteUniversity(Long id) {
        if (!universityRepository.existsById(id)) {
            throw new IllegalArgumentException("University not found");
        }
        universityRepository.deleteById(id);
    }



}
