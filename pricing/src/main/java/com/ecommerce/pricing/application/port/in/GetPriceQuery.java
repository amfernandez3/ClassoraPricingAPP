package com.ecommerce.pricing.application.port.in;

import com.ecommerce.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceQuery {
    Optional<Price> execute(LocalDateTime applicationDate, Long productId, Long brandId);
}
