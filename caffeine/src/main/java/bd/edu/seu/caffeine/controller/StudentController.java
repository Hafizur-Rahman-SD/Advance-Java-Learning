package bd.edu.seu.caffeine.controller;

import bd.edu.seu.caffeine.model.Student;
import bd.edu.seu.caffeine.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // cached endpoint
    @GetMapping("/{id}/cached")
    public ResponseEntity<?> getCached(@PathVariable int id) {
        long start = System.nanoTime();
        Student s = service.getStudentById(id);
        long ms = (System.nanoTime() - start) / 1_000_000;

        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response("CACHED", ms, s));
    }

    // nocache endpoint
    @GetMapping("/{id}/nocache")
    public ResponseEntity<?> getNoCache(@PathVariable int id) {
        long start = System.nanoTime();
        Student s = service.getStudentByIdNoCache(id);
        long ms = (System.nanoTime() - start) / 1_000_000;

        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response("NO_CACHE", ms, s));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Student student) {
        Student updated = service.updateStudent(id, student);
        return ResponseEntity.ok(updated);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    private Map<String, Object> response(String mode, long ms, Student s) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mode", mode);
        map.put("responseTimeMs", ms);
        map.put("student", s);
        return map;
    }
}
