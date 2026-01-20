package bd.edu.seu.retakequiz4.dto;

import bd.edu.seu.retakequiz4.model.OrderStatus;

import java.math.BigDecimal;

public class OrderUpsertRequest {

    private String customerName;
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatus status;

    public OrderUpsertRequest() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
