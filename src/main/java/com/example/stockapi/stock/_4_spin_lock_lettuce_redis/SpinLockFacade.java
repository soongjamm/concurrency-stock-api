package com.example.stockapi.stock._4_spin_lock_lettuce_redis;

import com.example.stockapi.stock.StockFacade;
import org.springframework.stereotype.Component;

@Component
public class SpinLockFacade implements StockFacade {

    private final SpinLockRepository spinLockRepository;
    private final SpinLockStockService spinLockService;

    public SpinLockFacade(SpinLockRepository spinLockRepository, SpinLockStockService spinLockService) {
        this.spinLockRepository = spinLockRepository;
        this.spinLockService = spinLockService;
    }

    public void deduction(String productName) {

    }

}
