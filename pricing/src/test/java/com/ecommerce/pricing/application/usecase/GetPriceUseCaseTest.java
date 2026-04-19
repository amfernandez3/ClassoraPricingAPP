package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.application.port.out.PriceRepository;
import com.ecommerce.pricing.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @Test
    @DisplayName("Debe retornar el precio cuando el repositorio encuentra una tarifa válida")
    void shouldReturnPriceWhenRepositoryReturnsData() {

        LocalDateTime ldt = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        Price mockPrice = new Price(
                brandId,                        // 1. Long brandId
                ldt.minusDays(1),               // 2. LocalDateTime startDate
                ldt.plusDays(1),                // 3. LocalDateTime endDate
                1,                              // 4. Integer priceList
                productId,                      // 5. Long productId
                0,                              // 6. Integer priority
                java.math.BigDecimal.valueOf(35.50), // 7. BigDecimal price
                "EUR"                           // 8. String curr
        );

        when(priceRepository.findRate(ldt, productId, brandId))
                .thenReturn(Optional.of(mockPrice));

        Optional<Price> result = getPriceUseCase.execute(ldt, productId, brandId);

        assertTrue(result.isPresent(), "El resultado debería estar presente");
        assertTrue(java.math.BigDecimal.valueOf(35.5).compareTo(result.get().price()) == 0);
        assertEquals("EUR", result.get().curr());

        verify(priceRepository, times(1)).findRate(ldt, productId, brandId);
    }

    @Test
    @DisplayName("Debe retornar Optional vacío cuando no hay tarifas para esa fecha")
    void shouldReturnEmptyWhenNoPriceIsFound() {

        LocalDateTime ldt = LocalDateTime.now();
        when(priceRepository.findRate(any(), anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        Optional<Price> result = getPriceUseCase.execute(ldt, 35455L, 1L);

        assertFalse(result.isPresent());
    }
}