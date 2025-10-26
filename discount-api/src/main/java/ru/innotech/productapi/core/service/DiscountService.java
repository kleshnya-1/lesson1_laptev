package ru.innotech.productapi.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innotech.productapi.adapters.discount.DiscountApi;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.adapters.repository.ProductRepository;
import ru.innotech.productapi.core.mapper.DiscountMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountApi discountApi;
    private final MetricsService metricsService;
    private final ProductRepository productRepository;
    private final DiscountMapper discountMapper;

    @Transactional(readOnly = true)
    public List<DiscountResponse> getDiscounts() {
        try (var od = MDC.putCloseable("od", "getDiscounts")) {
            log.debug("Get discounts: fetching all");
            List<DiscountResponse> result = productRepository.findAll().stream()
                    .map(discountMapper::toDto)
                    .collect(Collectors.toList());
            try (var size = MDC.putCloseable("batchSize", String.valueOf(result.size()))) {
                log.info("Get discounts: returned {}", result.size());
            }
            return result;
        } catch (Exception e) {
            metricsService.incrementErrorCounter("getDiscounts");
            log.error("Get discounts: failed - {}", e.getMessage());
            throw e;
        }
    }

}
