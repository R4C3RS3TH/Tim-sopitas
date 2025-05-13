package dbp.recuperation.hack1.productos.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dbp.recuperation.hack1.productos.domain.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
