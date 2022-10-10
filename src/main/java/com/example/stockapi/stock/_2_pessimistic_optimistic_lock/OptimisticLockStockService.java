package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockManagementService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OptimisticLockStockService implements StockManagementService {

    private final OptimisticLockStockRepository stockRepository;

    public OptimisticLockStockService(OptimisticLockStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional
    public void deduction(String productName) {
        Stock stock = stockRepository.findByProductName(productName);

        stock.decreaseQuantity();
    }
}
