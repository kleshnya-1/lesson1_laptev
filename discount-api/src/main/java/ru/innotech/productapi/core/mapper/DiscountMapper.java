package ru.innotech.productapi.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.model.Product;
import ru.innotech.productapi.core.model.ProductStatus;

import java.math.BigDecimal;

@Mapper(componentModel = "spring",
        uses = {},
        imports = {BigDecimal.class, ProductStatus.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountMapper {
    DiscountResponse toDto(Product product);
}
