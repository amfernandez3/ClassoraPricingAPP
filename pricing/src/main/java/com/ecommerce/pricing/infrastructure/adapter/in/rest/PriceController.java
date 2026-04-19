package com.ecommerce.pricing.infrastructure.adapter.in.rest;

import com.ecommerce.pricing.application.port.in.GetPriceQuery;
import com.ecommerce.pricing.domain.exception.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceQuery getPriceQuery;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        return getPriceQuery.execute(applicationDate, productId, brandId)
                .map(price -> new PriceResponse(
                        price.productId(),
                        price.brandId(),
                        price.priceList(),
                        price.startDate(),
                        price.endDate(),
                        price.price()
                ))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No se encontró precio para el producto %d y la cadena %d en la fecha %s",
                                productId, brandId, applicationDate)));
    }
}