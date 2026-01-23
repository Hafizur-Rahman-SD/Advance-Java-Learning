package bd.edu.seu.frontendapp.model;

import lombok.Data;

@Data
public class OrderDto {
    private String id;
    private Long customerId;
    private String productName;
    private Integer quantity;
    private Double price;
}
