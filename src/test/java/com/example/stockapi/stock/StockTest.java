package com.example.stockapi.stock;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StockTest {

    @Test
    void 재고_차감() {
        Stock sut = new Stock("상품1", 10);

        sut.decreaseQuantity();

        assertThat(sut.getQuantity()).isEqualTo(9);
    }
}