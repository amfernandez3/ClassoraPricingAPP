package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.application.port.in.GetPriceQuery;
import com.ecommerce.pricing.application.port.out.PriceRepository;
import com.ecommerce.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public class GetPriceUseCase implements GetPriceQuery {
    private final PriceRepository priceRepository;
    public GetPriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    @Override
    public Optional<Price> execute(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findRate(applicationDate, productId, brandId);
    }
}
