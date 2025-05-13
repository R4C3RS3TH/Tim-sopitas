package dbp.recuperation.hack1.event.events;

import org.springframework.context.ApplicationEvent;
import java.util.List;

public class OrderCreatedEvent extends ApplicationEvent {
    private final Long orderId;
    private final String email;
    private final List<String> products;

    public OrderCreatedEvent(Object source, Long orderId, String email, List<String> products) {
        super(source);
        this.orderId = orderId;
        this.email = email;
        this.products = products;
    }

    public Long getOrderId() { return orderId; }
    public String getEmail() { return email; }
    public List<String> getProducts() { return products; }
}
