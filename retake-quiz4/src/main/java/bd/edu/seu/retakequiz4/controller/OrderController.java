package bd.edu.seu.retakequiz4.controller;

import bd.edu.seu.retakequiz4.model.StoreOrder;
import bd.edu.seu.retakequiz4.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<StoreOrder> list(@RequestParam(required = false) String search) {
        return service.list(search);
    }

    @PostMapping
    public StoreOrder create(@RequestBody StoreOrder order) {
        return service.create(order);
    }

    @PutMapping("/{id}")
    public StoreOrder update(@PathVariable String id, @RequestBody StoreOrder order) {
        return service.update(id, order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
