package bd.edu.seu.orderservice.controller;

import bd.edu.seu.orderservice.model.Order;
import bd.edu.seu.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Order order) {
        try {
            return ResponseEntity.ok(service.createOrder(order));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Order> byCustomer(@PathVariable Long customerId) {
        return service.getByCustomerId(customerId);
    }
}
