package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService implements StockManagementService {

    private final PessimisticLockStockRepository stockRepository;

    public PessimisticLockStockService(PessimisticLockStockRepository pessimisticLockStockRepository) {
        this.stockRepository = pessimisticLockStockRepository;
    }

    @Override
    @Transactional
    public void deduction(String productName) {
        Stock stock = stockRepository.findByProductName(productName);

        stock.decreaseQuantity();
    }
}
