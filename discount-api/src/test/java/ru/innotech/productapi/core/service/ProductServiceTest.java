package ru.innotech.productapi.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innotech.productapi.ProductTestUtil;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.adapters.repository.ProductRepository;
import ru.innotech.productapi.core.mapper.DiscountMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private DiscountMapper discountMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MetricsService metricsService;

    @InjectMocks
    private DiscountService service;


    @Test
    void whenGetAllDiscountsThenSuccessTest() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(ProductTestUtil.product1Mock(), ProductTestUtil.product2Mock()));
        DiscountResponse responseElement1 = ProductTestUtil.discount1ResponseMock(null);
        DiscountResponse responseElement2 = ProductTestUtil.discount2ResponseMock(963L);

        Mockito.when(discountMapper.toDto(ProductTestUtil.product1Mock())).thenReturn(responseElement1);
        Mockito.when(discountMapper.toDto(ProductTestUtil.product2Mock())).thenReturn(responseElement2);
        List<DiscountResponse> expected = List.of(responseElement1, responseElement2);
        List<DiscountResponse> actual = service.getDiscounts();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void whenGetAllDiscountsThrowExceptionTest() {
        String exceptionText = "exc1";
        Mockito.when(productRepository.findAll()).thenThrow(new RuntimeException(exceptionText));

        RuntimeException actualException = Assertions.assertThrows(
                RuntimeException.class, () -> {service.getDiscounts();});
        Assertions.assertTrue(actualException.getMessage().contains(exceptionText));
    }
}
