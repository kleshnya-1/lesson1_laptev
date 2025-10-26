package ru.innotech.productapi;

import lombok.experimental.UtilityClass;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.model.Product;
import ru.innotech.productapi.core.model.ProductStatus;

import java.math.BigDecimal;

@UtilityClass
public class ProductTestUtil {
    public static Product product1Mock() {
        return Product.builder()
                .name("Product1")
                .description("Description of product1")
                .price(BigDecimal.valueOf(100))
                .currency("RUB")
                .discount(BigDecimal.ZERO)
                .status(ProductStatus.ACTIVE)
                .build();
    }

    public static Product product2Mock() {
        return Product.builder()
                .name("Product2")
                .description("Description of product2")
                .price(BigDecimal.valueOf(200))
                .currency("RUB")
                .discount(BigDecimal.ZERO)
                .status(ProductStatus.ACTIVE)
                .build();
    }

    public static DiscountResponse discount1ResponseMock(Long id) {
        return new DiscountResponse(
                id,
                BigDecimal.ZERO);
    }

    public static DiscountResponse discount2ResponseMock(Long id) {
        return new DiscountResponse(
                id,
                BigDecimal.valueOf(200));
    }
}
