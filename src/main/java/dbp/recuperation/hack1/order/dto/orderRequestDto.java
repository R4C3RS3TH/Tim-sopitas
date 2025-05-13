package dbp.recuperation.hack1.order.dto;

import lombok.Data;

@Data
public class orderRequestDto {
    private String email;
    private List<Producto> product;
}
