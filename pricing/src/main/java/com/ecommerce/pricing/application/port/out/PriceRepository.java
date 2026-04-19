package com.ecommerce.pricing.application.port.out;

import com.ecommerce.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findRate(LocalDateTime applicationDate, Long productId, Long brandId);
}
