package dbp.recuperation.hack1.productos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dbp.recuperation.hack1.order.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Default
    private Long stock = 1L;

    // @ManyToOne
    // @JoinColumn(name = "order_id")
    // @JsonIgnore
    // private Order order;
}
