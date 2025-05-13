package dbp.recuperation.hack1.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dbp.recuperation.hack1.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
