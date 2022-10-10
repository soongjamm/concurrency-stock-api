package com.example.stockapi.stock._1_synchronized;

import com.example.stockapi.stock.ConcurrencyTestTemplate;
import com.example.stockapi.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class StockServiceTest extends ConcurrencyTestTemplate {

    @Autowired
    private SynchronizedStockService synchronizedStockService;

    @Autowired
    private SynchronizedWithTransactionalStockService synchronizedWithTransactionalStockService;

    @Test
    @DisplayName("synchronized : 약 760ms (동시성제어 안하면 약 4xx ms)")
    void 동시에_n개의_재고차감을_요청을_받으면_n개가_차감된다() throws InterruptedException {
        stockRepository.saveAndFlush(new Stock("상품1", initQuantity));

        for (int i = 0; i < initQuantity; i++) {
            es.submit(() -> {
                synchronizedStockService.deduction("상품1");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();


        Stock stock = stockRepository.findByProductName("상품1");
        assertThat(stock.getQuantity()).isZero();
    }

    @Test
    void 실패() throws InterruptedException {
        stockRepository.saveAndFlush(new Stock("상품1", initQuantity));


        for (int i = 0; i < initQuantity; i++) {
            es.submit(() -> {
                synchronizedWithTransactionalStockService.deduction("상품1");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();


        Stock stock = stockRepository.findByProductName("상품1");
        assertThat(stock.getQuantity()).isNotZero();
        System.out.println(stock.getQuantity());
    }
}