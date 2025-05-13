package dbp.recuperation.hack1.order.dto;

import dbp.recuperation.hack1.productos.dto.ProductoDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private String email;
    private List<ProductoDto> product;
}
