package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.ConcurrencyTestTemplate;
import com.example.stockapi.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class PessimisticStockServiceTest extends ConcurrencyTestTemplate {

    @Autowired
    private PessimisticLockService pessimisticLockService;

    @Test
    @DisplayName("pessimistic lock : 1sec 82ms")
    void 동시에_n개의_재고차감을_요청을_받으면_n개가_차감된다() throws InterruptedException {

        for (int i = 0; i < initQuantity; i++) {
            es.submit(() -> {
                pessimisticLockService.deduction("상품1");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(); // 초기 데이터를 넣지 않았더니 NPE 가 발생하고 무한대기에 빠짐


        Stock stock = stockRepository.findByProductName("상품1");
        assertThat(stock.getQuantity()).isZero();
    }
}