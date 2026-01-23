package bd.edu.seu.customerservice.controller;

import bd.edu.seu.customerservice.model.Customer;
import bd.edu.seu.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // Register customer
    @PostMapping
    public Customer register(@RequestBody Customer customer) {
        return service.save(customer);
    }

    // View customer profile
    @GetMapping("/{id}")
    public ResponseEntity<Customer> profile(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // List all customers for frontend app
    @GetMapping
    public List<Customer> allCustomers() {
        return service.findAll();
    }
}
