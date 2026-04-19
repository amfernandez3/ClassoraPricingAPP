package com.ecommerce.pricing.infrastructure.adapter.out.persistence;

import com.ecommerce.pricing.application.port.out.PriceRepository;
import com.ecommerce.pricing.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceRepository {

    private final SpringDataPriceRepository repository;

    @Override
    public Optional<Price> findRate(LocalDateTime applicationDate, Long productId, Long brandId) {
        return repository.findRate(applicationDate, productId, brandId)
                .map(this::mapToDomain);
    }

    // El traductor: de Entidad (infra) a Record (dominio)
    private Price mapToDomain(PriceEntity entity) {
        return new Price(
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr()
        );
    }
}