package dbp.recuperation.hack1.order.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import dbp.recuperation.hack1.event.events.OrderCreatedEvent;
import dbp.recuperation.hack1.order.dto.OrderRequestDto;
import dbp.recuperation.hack1.order.infrastructure.OrderRepository;
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
        orden.getProducts().forEach(producto -> {
            productoRepository.save(producto);
        });
        Order order = Order.builder()
                .email(orden.getEmail())
                .productos(productoRepository.findAll())
                .build();
        orderRepository.save(order);
        applicationEventPublisher
                .publishEvent(new OrderCreatedEvent(this, order.getId(), order.getEmail(),
                        order.getProductos().stream()
                                .map(producto -> ProductoDto.builder().nombre(producto.getNombre()).build()).toList()));
        return "Order saved";
    }
}
