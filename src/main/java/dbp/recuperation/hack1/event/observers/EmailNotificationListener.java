package dbp.recuperation.hack1.event.observers;

import com.tuempresa.events.OrderCreatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {
    private static final Logger logger = LogManager.getLogger(EmailNotificationListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Simulamos envío de email con un log
        logger.info("Enviando email de confirmación para pedido {} al usuario {}",
                event.getOrderId(), event.getEmail());
    }
}