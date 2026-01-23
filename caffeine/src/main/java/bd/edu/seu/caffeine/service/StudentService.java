package bd.edu.seu.caffeine.service;

import bd.edu.seu.caffeine.model.Student;
import bd.edu.seu.caffeine.Repository.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // Cache getStudentById(int id)
    @Cacheable(value = "students", key = "#id")
    public Student getStudentById(int id) {
        return repo.findById(id);
    }

    // ensure cache is updated immediately after modification
    @CachePut(cacheNames = "students", key = "#id")
    public Student updateStudent(int id, Student student) {
        return repo.update(id, student);
    }

    // ensure cache is deleted immediately after deletion
    @CacheEvict(cacheNames = "students", key = "#id")
    public void deleteStudent(int id) {
        repo.delete(id);
    }

    // No-cache version
    public Student getStudentByIdNoCache(int id) {
        return repo.findById(id);
    }
}
