package bd.edu.seu.frontendapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient customerWebClient(WebClient.Builder builder,
                                       @Value("${customer.service.base-url}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }

    @Bean
    public WebClient orderWebClient(WebClient.Builder builder,
                                    @Value("${order.service.base-url}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
