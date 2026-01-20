package bd.edu.seu.retakequiz4.repo;

import bd.edu.seu.retakequiz4.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);
}
