package bd.edu.seu.retakequiz4.controller;

import bd.edu.seu.retakequiz4.model.Product;
import bd.edu.seu.retakequiz4.repo.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }
}
