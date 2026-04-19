package com.ecommerce.pricing.infrastructure.config;

import com.ecommerce.pricing.application.port.out.PriceRepository;
import com.ecommerce.pricing.application.usecase.GetPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public GetPriceUseCase getPriceUseCase(PriceRepository priceRepository) {
        return new GetPriceUseCase(priceRepository);
    }
}