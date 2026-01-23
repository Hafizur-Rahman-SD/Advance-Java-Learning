package bd.edu.seu.orderservice.service;

import bd.edu.seu.orderservice.model.Order;
import bd.edu.seu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;

    public Order createOrder(Order order) {
        if (order.getCustomerId() == null) {
            throw new IllegalArgumentException("customerId is required");
        }

        boolean exists = customerClient.customerExists(order.getCustomerId());
        if (!exists) {
            throw new IllegalArgumentException("Customer not found in Customer Service");
        }

        return orderRepository.save(order);
    }

    public Optional<Order> getById(String id) {
        return orderRepository.findById(id);
    }

    public List<Order> getByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
