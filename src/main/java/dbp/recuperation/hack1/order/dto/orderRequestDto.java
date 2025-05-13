package dbp.recuperation.hack1.order.dto;

import dbp.recuperation.hack1.productos.domain.Producto;
import lombok.Data;

import java.util.List;

@Data
public class orderRequestDto {
    private String email;
    private List<Producto> product;
}
