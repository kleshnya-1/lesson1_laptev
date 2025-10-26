package ru.innotech.productapi.adapters.controller;

import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.innotech.productapi.ProductTestUtil;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.model.Product;

import java.math.BigDecimal;
import java.util.List;

class ProductControllerTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("\"интеграционные тесты для тестирования бд\" включены в этот тест")
    void whenGetAllDiscountsThenSuccessTest() throws Exception {
        Product product1 = ProductTestUtil.product1Mock();
        Product product2 = ProductTestUtil.product2Mock();
        productRepository.save(product1);
        productRepository.save(product2);

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
                .filter(dr -> dr.productId().equals(product1.getId()))
                .findFirst()
                .get().discount();
        BigDecimal product2DiscountActual = discountResponse.stream()
                .filter(dr -> dr.productId().equals(product2.getId()))
                .findFirst()
                .get().discount();
        Assertions.assertEquals(product1.getDiscount(), product1DiscountActual);
        Assertions.assertEquals(product2.getDiscount(), product2DiscountActual);
    }
}
