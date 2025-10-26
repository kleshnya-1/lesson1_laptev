package ru.innotech.productapi.adapters.controller;

import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.innotech.discountctapi.AbstractIntegrationTest;
import ru.innotech.productapi.DiscountTestUtil;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.model.Discount;


import java.math.BigDecimal;
import java.util.List;

class DiscountControllerTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("\"интеграционные тесты для тестирования бд\" включены в этот тест")
    void whenGetAllDiscountsThenSuccessTest() throws Exception {
        Discount discount1 = DiscountTestUtil.product1Mock();
        Discount discount2 = DiscountTestUtil.product2Mock();
        discountRepository.save(discount1);
        discountRepository.save(discount2);

        String jsonResponse = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/v1/discounts")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<DiscountResponse> discountResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        BigDecimal product1DiscountActual = discountResponse.stream()
                .filter(dr -> dr.productId().equals(discount1.getProductId()))
                .findFirst()
                .get().discount();
        BigDecimal product2DiscountActual = discountResponse.stream()
                .filter(dr -> dr.productId().equals(discount2.getProductId()))
                .findFirst()
                .get().discount();
        Assertions.assertEquals(0, discount1.getDiscount().compareTo(product1DiscountActual));
        Assertions.assertEquals(0, discount2.getDiscount().compareTo(product2DiscountActual));
    }
}
