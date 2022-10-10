package com.example.stockapi.stock._4_spin_lock_lettuce_redis;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockManagementService;
import com.example.stockapi.stock.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpinLockStockService implements StockManagementService {

    private final StockRepository stockRepository;

    public SpinLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional
    public void deduction(String productName) {
        Stock stock = stockRepository.findByProductName(productName);
        stock.decreaseQuantity();
    }
}
