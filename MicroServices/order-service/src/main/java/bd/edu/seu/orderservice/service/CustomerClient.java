package bd.edu.seu.orderservice.service;

import bd.edu.seu.orderservice.model.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CustomerClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${customer.service.base-url}")
    private String customerServiceBaseUrl;

    public boolean customerExists(Long customerId) {
        try {
            CustomerDto customer = webClientBuilder
                    .baseUrl(customerServiceBaseUrl)
                    .build()
                    .get()
                    .uri("/customers/{id}", customerId)
                    .retrieve()
                    .bodyToMono(CustomerDto.class)
                    .block();

            return customer != null && customer.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
