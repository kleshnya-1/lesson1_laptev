package ru.innotech.productapi;

import lombok.experimental.UtilityClass;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.model.Discount;

import java.math.BigDecimal;

@UtilityClass
public class DiscountTestUtil {
    public static Discount product1Mock() {
        return Discount.builder()
                .productId(1L)
                .discount(BigDecimal.ZERO)
                .build();
    }

    public static Discount product2Mock() {
        return Discount.builder()
                .productId(2L)
                .discount(BigDecimal.valueOf(200))
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
