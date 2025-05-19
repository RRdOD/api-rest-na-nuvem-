// ProductApiApplication.java

package com.example.productapi;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import jakarta.persistence.*;
import java.util.List;

@SpringBootApplication
public class ProductApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }

    // --- ENTIDADE ---
    @Entity
    class Product {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;
        public String name;
        public Double price;
    }

    // --- REPOSITÓRIO ---
    interface ProductRepository extends JpaRepository<Product, Long> {}

    // --- CONTROLLER ---
    @RestController
    @RequestMapping("/api/products")
    class ProductController {
        private final ProductRepository repository;

        ProductController(ProductRepository repository) {
            this.repository = repository;
        }

        @GetMapping
        public List<Product> list() {
            return repository.findAll();
        }

        @PostMapping
        public Product create(@RequestBody Product p) {
            return repository.save(p);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            repository.deleteById(id);
        }
    }
}
