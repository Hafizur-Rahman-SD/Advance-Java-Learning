package bd.edu.seu.customerservice.service;

import bd.edu.seu.customerservice.model.Customer;
import bd.edu.seu.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }
}
