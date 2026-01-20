package bd.edu.seu.retakequiz4.seed;

import bd.edu.seu.retakequiz4.model.AppUser;
import bd.edu.seu.retakequiz4.model.Product;
import bd.edu.seu.retakequiz4.repo.ProductRepository;
import bd.edu.seu.retakequiz4.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public DataSeeder(ProductRepository productRepo, UserRepository userRepo, PasswordEncoder encoder) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        if (userRepo.findByUsername("admin").isEmpty()) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPasswordHash(encoder.encode("admin123"));
            admin.setRoles(Set.of("ADMIN"));
            userRepo.save(admin);
        }

        if (productRepo.count() == 0) {
            Product p1 = new Product();
            p1.setName("Wireless Headphones");
            p1.setPrice(new BigDecimal("129"));

            Product p2 = new Product();
            p2.setName("Gaming Keyboard");
            p2.setPrice(new BigDecimal("99"));

            Product p3 = new Product();
            p3.setName("Office Chair");
            p3.setPrice(new BigDecimal("159"));

            productRepo.save(p1);
            productRepo.save(p2);
            productRepo.save(p3);
        }
    }
}
