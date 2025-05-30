package dbp.recuperation.hack1.event.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import dbp.recuperation.hack1.productos.domain.Producto;

import java.util.List;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {
    private final Long orderId;
    private final String email;
    private final List<Producto> products;

    public OrderCreatedEvent(Object source, Long orderId, String email, List<Producto> products) {
        super(source);
        this.orderId = orderId;
        this.email = email;
        this.products = products;
    }

}
