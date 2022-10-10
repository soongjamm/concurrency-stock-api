package com.example.stockapi.stock._4_spin_lock_lettuce_redis;

import com.example.stockapi.stock.StockFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SpinLockStockFacade implements StockFacade {

    private static final Logger LOG = LoggerFactory.getLogger(SpinLockStockFacade.class);
    private final SpinLockRepository spinLockRepository;
    private final SpinLockStockService spinLockStockService;

    public SpinLockStockFacade(SpinLockRepository spinLockRepository, SpinLockStockService spinLockStockService) {
        this.spinLockRepository = spinLockRepository;
        this.spinLockStockService = spinLockStockService;
    }

    public void deduction(String productName) {

        while(failedToGetLock(productName)) {
            sleep(100L);
        }

        try {
            spinLockStockService.deduction(productName);
        } catch (Exception e) {
            LOG.error("재고 차감 중 에러 발생 {}", e.getMessage());
        } finally {
            spinLockRepository.releaseLock(productName);
        }

    }

    private void sleep(Long milliSec) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSec);
        } catch (InterruptedException e) {
            LOG.info("Interrupted!", e);
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }

    private boolean failedToGetLock(String productName) {
        return !spinLockRepository.getLock(productName);
    }

}
