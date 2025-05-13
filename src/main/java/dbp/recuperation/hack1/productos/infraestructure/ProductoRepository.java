package dbp.recuperation.hack1.productos.infraestructure;

import dbp.recuperation.hack1.productos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    Optional<Producto> findById(String id);
}
