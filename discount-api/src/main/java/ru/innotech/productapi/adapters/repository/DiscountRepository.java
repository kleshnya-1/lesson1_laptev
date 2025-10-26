package ru.innotech.productapi.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innotech.productapi.core.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
