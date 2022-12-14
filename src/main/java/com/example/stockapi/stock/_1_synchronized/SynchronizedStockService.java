package com.example.stockapi.stock._1_synchronized;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockManagementService;
import org.springframework.stereotype.Service;

@Service
public class SynchronizedStockService implements StockManagementService {

    private final NormalStockRepository normalStockRepository;

    public SynchronizedStockService(NormalStockRepository normalStockRepository) {
        this.normalStockRepository = normalStockRepository;
    }

    public synchronized void deduction(String productName) {
        Stock stock = normalStockRepository.findByProductName(productName);

        stock.decreaseQuantity();

        normalStockRepository.save(stock);
    }

}
