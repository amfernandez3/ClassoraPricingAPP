package com.ecommerce.pricing.infrastructure.adapter.in.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para brand 1 (ZARA)")
    void test1() throws Exception {
        checkPrice("2020-06-14T10:00:00", 35.50, 1);
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para brand 1 (ZARA)")
    void test2() throws Exception {
        checkPrice("2020-06-14T16:00:00", 25.45, 2);
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para brand 1 (ZARA)")
    void test3() throws Exception {
        checkPrice("2020-06-14T21:00:00", 35.50, 1);
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para brand 1 (ZARA)")
    void test4() throws Exception {
        checkPrice("2020-06-15T10:00:00", 30.50, 3);
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para brand 1 (ZARA)")
    void test5() throws Exception {
        checkPrice("2020-06-16T21:00:00", 38.95, 4);
    }

    private void checkPrice(String date, double expectedPrice, int expectedList) throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", date)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(expectedPrice)))
                .andExpect(jsonPath("$.priceList", is(expectedList)))
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)));
    }
}