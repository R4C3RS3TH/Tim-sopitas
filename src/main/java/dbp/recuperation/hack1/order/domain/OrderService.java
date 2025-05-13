package dbp.recuperation.hack1.order.domain;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import dbp.recuperation.hack1.event.events.OrderCreatedEvent;
import dbp.recuperation.hack1.order.dto.OrderRequestDto;
import dbp.recuperation.hack1.order.infrastructure.OrderRepository;
import dbp.recuperation.hack1.productos.domain.Producto;
import dbp.recuperation.hack1.productos.dto.ProductoDto;
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
                                .map(producto -> Producto.builder()
                                                .nombre(producto.getNombre())
                                                .order(savedOrder) // ✅ Usa la order persistida
                                                .build())
                                .toList();

                // 3. Guardar todos los productos
                productoRepository.saveAll(productos);

                // 4. Asignar los productos a la order (opcional si lo necesitas después)
                savedOrder.setProductos(productos);

                // 5. Publicar el evento
                applicationEventPublisher.publishEvent(
                                new OrderCreatedEvent(this, savedOrder.getId(), savedOrder.getEmail(),
                                                productos.stream()
                                                                .map(producto -> ProductoDto.builder()
                                                                                .nombre(producto.getNombre()).build())
                                                                .toList()));
                return "Order saved";
        }
}
