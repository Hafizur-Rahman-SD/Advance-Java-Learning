package bd.edu.seu.caffeine.Repository;

import bd.edu.seu.caffeine.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {

    private final Map<Integer, Student> store = new ConcurrentHashMap<>();

    public StudentRepository() {
        store.put(1, new Student(1, "Hafizur"));
        store.put(2, new Student(2, "Rahman"));
        store.put(3, new Student(3, "Rony"));
    }

    public Student findById(int id) {
        slowDb();
        Student s = store.get(id);
        if (s == null) return null;
        return new Student(s.getId(), s.getName());
    }

    public Student update(int id, Student student) {
        slowDb();
        Student updated = new Student(id, student.getName());
        store.put(id, updated);
        return new Student(updated.getId(), updated.getName());
    }

    public void delete(int id) {
        slowDb();
        store.remove(id);
    }

    private void slowDb() {
        try { Thread.sleep(700); } catch (InterruptedException ignored) {}
    }
}
