package dbp.recuperation.hack1.event.observers;

import dbp.recuperation.hack1.event.events.OrderCreatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdateListener {
    private static final Logger logger = LogManager.getLogger(InventoryUpdateListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        event.getProducts().forEach(product ->
                logger.info("Reduciendo stock del producto {}", product)
        );
    }
}

