package dbp.recuperation.hack1.event.observers;

import dbp.recuperation.hack1.event.events.OrderCreatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogListener {
    private static final Logger logger = LogManager.getLogger(AuditLogListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Registramos un audit log del pedido
        logger.info("AUDIT: Pedido registrado -> id={} email={} productos={}",
                event.getOrderId(), event.getEmail(), event.getProducts());
    }
}
