package ru.innotech.productapi.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.model.Discount;

@Mapper(componentModel = "spring",
        uses = {},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountMapper {
    DiscountResponse toDto(Discount discount);
}
