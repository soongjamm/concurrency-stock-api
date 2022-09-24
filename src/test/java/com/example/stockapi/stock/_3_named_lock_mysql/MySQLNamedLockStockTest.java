package com.example.stockapi.stock._3_named_lock_mysql;

import com.example.stockapi.stock.ConcurrencyTestTemplate;
import com.example.stockapi.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class MySQLNamedLockStockTest extends ConcurrencyTestTemplate {

    @Autowired
    private NamedLockFacade namedLockFacade;

    @Test
    @DisplayName("Named Lock (MySQL get_lock()) : 4sec 959ms")
    void 동시에_n개의_재고차감을_요청을_받으면_n개가_차감된다() throws InterruptedException {

        for (int i = 0; i < initQuantity; i++) {
            es.submit(() -> {
                namedLockFacade.deduction("상품1");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        Stock stock = stockRepository.findByProductName("상품1");
        assertThat(stock.getQuantity()).isZero();
    }
}