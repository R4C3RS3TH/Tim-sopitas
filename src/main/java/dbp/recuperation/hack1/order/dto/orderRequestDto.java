package dbp.recuperation.hack1.order.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String email;
    private String product;
    private int quantity;
    private String address;
    private String paymentMethod;
    private String orderStatus;

    public OrderRequestDto(String email, String product, int quantity, String address, String paymentMethod,
            String orderStatus) {
        this.email = email;
        this.product = product;
        this.quantity = quantity;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }
}
