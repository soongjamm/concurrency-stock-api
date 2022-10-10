package com.example.stockapi.stock._3_named_lock_mysql;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockManagementService;
import com.example.stockapi.stock.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NamedLockStockService implements StockManagementService {

    private final StockRepository stockRepository;

    public NamedLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deduction(String productName) {
        Stock stock = stockRepository.findByProductName(productName);
        stock.decreaseQuantity();
    }
}
