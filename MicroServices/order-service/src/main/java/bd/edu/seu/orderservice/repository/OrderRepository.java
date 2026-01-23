package bd.edu.seu.orderservice.repository;

import bd.edu.seu.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomerId(Long customerId);
}
