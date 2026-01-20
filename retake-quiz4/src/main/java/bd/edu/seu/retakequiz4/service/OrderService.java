package bd.edu.seu.retakequiz4.service;

import bd.edu.seu.retakequiz4.model.StoreOrder;
import bd.edu.seu.retakequiz4.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<StoreOrder> list(String search) {
        if (search != null && !search.isBlank()) {
            return repo.findByCustomerNameContainingIgnoreCaseOrderByCreatedAtDesc(search);
        }
        return repo.findTop20ByOrderByCreatedAtDesc();
    }

    public StoreOrder create(StoreOrder order) {
        order.setId(null);
        order.setCreatedAt(Instant.now());
        return repo.save(order);
    }

    public StoreOrder update(String id, StoreOrder order) {
        StoreOrder old = repo.findById(id).orElseThrow();

        old.setCustomerName(order.getCustomerName());
        old.setProductId(order.getProductId());
        old.setProductName(order.getProductName());
        old.setQuantity(order.getQuantity());
        old.setTotalPrice(order.getTotalPrice());
        old.setStatus(order.getStatus());

        return repo.save(old);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
