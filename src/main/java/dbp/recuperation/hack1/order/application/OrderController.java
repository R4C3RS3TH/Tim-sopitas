package dbp.recuperation.hack1.order.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dbp.recuperation.hack1.order.domain.OrderService;
import dbp.recuperation.hack1.order.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> recibirPedido(@RequestBody OrderRequestDto orden) {
        String mensaje = orderService.save(orden);
        return ResponseEntity.ok(mensaje);
    }
}
