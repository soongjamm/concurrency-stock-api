package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.ConcurrencyTestTemplate;
import com.example.stockapi.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class OptimisticStockTest extends ConcurrencyTestTemplate {

    @Autowired
    private OptimisticLockStockFacade optimisticLockStockFacade;

    @Test
    @DisplayName("optimistic lock : 4sec 898ms")
    void 동시에_n개의_재고차감을_요청을_받으면_n개가_차감된다() throws InterruptedException {

        for (int i = 0; i < initQuantity; i++) {
            es.submit(() -> {
                try {
                    optimisticLockStockFacade.deduction("상품1");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();


        Stock stock = stockRepository.findByProductName("상품1");
        assertThat(stock.getQuantity()).isZero();
    }
}