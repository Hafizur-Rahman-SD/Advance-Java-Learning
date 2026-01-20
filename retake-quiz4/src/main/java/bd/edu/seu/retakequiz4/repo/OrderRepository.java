package bd.edu.seu.retakequiz4.repo;

import bd.edu.seu.retakequiz4.model.OrderStatus;
import bd.edu.seu.retakequiz4.model.StoreOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends MongoRepository<StoreOrder, String> {

    List<StoreOrder> findTop20ByOrderByCreatedAtDesc();

    List<StoreOrder> findByCustomerNameContainingIgnoreCaseOrderByCreatedAtDesc(String q);

    long countByCreatedAtBetween(Instant start, Instant end);

    long countByStatusAndCreatedAtBetween(OrderStatus status, Instant start, Instant end);
}
