package ru.innotech.productapi.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innotech.productapi.core.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
