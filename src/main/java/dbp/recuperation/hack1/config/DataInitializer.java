package dbp.recuperation.hack1.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbp.recuperation.hack1.productos.domain.Producto;
import dbp.recuperation.hack1.productos.infraestructure.ProductoRepository;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(ProductoRepository productoRepository) {
        return args -> {
            if (productoRepository.count() == 0) {
                productoRepository.saveAll(List.of(
                        new Producto(null, "Producto A", 100L),
                        new Producto(null, "Producto B", 200L),
                        new Producto(null, "Producto C", 300L)));
                System.out.println("Datos iniciales cargados.");
            }
        };
    }
}
