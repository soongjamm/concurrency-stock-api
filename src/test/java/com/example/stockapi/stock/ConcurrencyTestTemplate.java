package com.example.stockapi.stock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public abstract class ConcurrencyTestTemplate {

    @Autowired
    protected StockRepository stockRepository;

    protected int numberOfThreads = 100;
    protected int initQuantity = 1000;
    protected ExecutorService es;
    protected CountDownLatch countDownLatch;

    @BeforeEach
    void setUp() {
        es = Executors.newFixedThreadPool(numberOfThreads);
        countDownLatch = new CountDownLatch(initQuantity);
        stockRepository.save(new Stock("상품1", initQuantity));
    }


    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }

}
