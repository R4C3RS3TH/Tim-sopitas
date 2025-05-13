package dbp.recuperation.hack1.order.domain;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import dbp.recuperation.hack1.event.events.OrderCreatedEvent;
import dbp.recuperation.hack1.order.dto.OrderRequestDto;
import dbp.recuperation.hack1.order.infrastructure.OrderRepository;
import dbp.recuperation.hack1.productos.domain.Producto;
import dbp.recuperation.hack1.productos.infraestructure.ProductoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
        private final OrderRepository orderRepository;
        private final ProductoRepository productoRepository;
        private final ApplicationEventPublisher applicationEventPublisher;

        public String save(OrderRequestDto orden) {
                Order order = Order.builder()
                                .email(orden.getEmail())
                                .build();
                Order savedOrder = orderRepository.save(order); // ✅ Primero persiste Order

                // 2. Crear productos usando la Order persistida

                List<Producto> productos = orden.getProduct().stream()
                                .map(productoDto -> {
                                        Producto producto = productoRepository.findByNombre(productoDto.getNombre())
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "Producto no encontrado: "
                                                                                        + productoDto.getNombre()));

                                        // Verificar si hay stock suficiente
                                        if (producto.getStock() <= 0) {
                                                throw new RuntimeException("No hay stock suficiente para el producto: "
                                                                + producto.getNombre());
                                        }

                                        // Reducir el stock
                                        producto.setStock(producto.getStock() - 1); // o usa productoDto.getCantidad()
                                                                                    // si manejas cantidades

                                        return producto;
                                })
                                .toList();

                // 3. Guardar todos los productos
                productoRepository.saveAll(productos);

                // 4. Asignar los productos a la order (opcional si lo necesitas después)
                savedOrder.setProductos(productos);

                // 5. Publicar el evento
                applicationEventPublisher.publishEvent(
                                new OrderCreatedEvent(this, savedOrder.getId(), savedOrder.getEmail(),
                                                productos));
                return "Order saved";
        }

        public List<Order> getAll() {
                return orderRepository.findAll();
        }
}
