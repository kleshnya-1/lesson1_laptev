package ru.innotech.productapi.adapters.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.innotech.productapi.adapters.discount.dto.DiscountResponse;
import ru.innotech.productapi.core.service.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService service;

    @GetMapping()
    public ResponseEntity<List<DiscountResponse>> getDiscounts() {
        return ResponseEntity.ok(service.getDiscounts());
    }
}
