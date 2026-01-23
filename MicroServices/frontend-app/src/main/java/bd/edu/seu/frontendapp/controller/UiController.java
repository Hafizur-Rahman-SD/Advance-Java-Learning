package bd.edu.seu.frontendapp.controller;

import bd.edu.seu.frontendapp.model.CustomerDto;
import bd.edu.seu.frontendapp.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class UiController {

    private final WebClient customerWebClient;
    private final WebClient orderWebClient;

    @GetMapping
    public String home() {
        return "redirect:/ui/customers";
    }

    // Show register form
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        model.addAttribute("error", null);
        return "register";
    }

    // Submit registration
    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("customer") CustomerDto customer, Model model) {
        try {
            customerWebClient.post()
                    .uri("/customers")
                    .bodyValue(customer)
                    .retrieve()
                    .bodyToMono(CustomerDto.class)
                    .block();
            return "redirect:/ui/customers";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Maybe email already exists.");
            return "register";
        }
    }

    // List customers
    @GetMapping("/customers")
    public String customers(Model model) {
        List<CustomerDto> customers = customerWebClient.get()
                .uri("/customers")
                .retrieve()
                .bodyToFlux(CustomerDto.class)
                .collectList()
                .block();

        model.addAttribute("customers", customers);
        return "customers";
    }

    // Profile page
    @GetMapping("/customers/{id}")
    public String profile(@PathVariable Long id, Model model) {
        CustomerDto customer = customerWebClient.get()
                .uri("/customers/{id}", id)
                .retrieve()
                .bodyToMono(CustomerDto.class)
                .block();

        model.addAttribute("customer", customer);
        return "profile";
    }

    // Order form
    @GetMapping("/orders/new")
    public String orderForm(@RequestParam Long customerId, Model model) {
        OrderDto order = new OrderDto();
        order.setCustomerId(customerId);

        model.addAttribute("order", order);
        model.addAttribute("error", null);
        return "order";
    }

    // Place order
    @PostMapping("/orders")
    public String placeOrder(@ModelAttribute("order") OrderDto order, Model model) {
        try {
            orderWebClient.post()
                    .uri("/orders")
                    .bodyValue(order)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return "redirect:/ui/customers/" + order.getCustomerId();
        } catch (Exception e) {
            model.addAttribute("error", "Order failed. Ensure customer exists and order-service is running.");
            return "order";
        }
    }
}
