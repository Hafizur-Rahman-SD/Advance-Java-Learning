package bd.edu.seu.retakequiz4.repo;

import bd.edu.seu.retakequiz4.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
