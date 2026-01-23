package bd.edu.seu.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    private Long customerId;
    private String productName;
    private Integer quantity;
    private Double price;

    private Instant createdAt = Instant.now();
}
