package bd.edu.seu.customerservice.repository;

import bd.edu.seu.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
