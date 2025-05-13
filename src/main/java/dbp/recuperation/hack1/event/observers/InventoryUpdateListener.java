package dbp.recuperation.hack1.event.observers;

import dbp.recuperation.hack1.event.events.OrderCreatedEvent;
import dbp.recuperation.hack1.productos.domain.Producto;
import dbp.recuperation.hack1.productos.infraestructure.ProductoRepository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdateListener {
    private static final Logger logger = LogManager.getLogger(InventoryUpdateListener.class);
    private final ProductoRepository productoRepository;

    public InventoryUpdateListener(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        List<Long> productoIds = event.getProducts().stream()
                .map(Producto::getId) // Asegúrate de que el DTO tenga el ID
                .toList();

        // Cargar los productos desde la base de datos
        List<Producto> productos = productoRepository.findAllById(productoIds);

        productos.forEach(producto -> {
            Long nuevoStock = producto.getStock();
            logger.info("Reduciendo stock del producto: {}",
                    producto.getStock());
            producto.setStock(nuevoStock);
        });

        // Guardar los productos actualizados
        productoRepository.saveAll(productos);
    }
}
