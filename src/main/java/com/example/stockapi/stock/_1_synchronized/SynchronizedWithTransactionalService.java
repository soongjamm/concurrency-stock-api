package com.example.stockapi.stock._1_synchronized;

import com.example.stockapi.stock.Stock;
import com.example.stockapi.stock.StockManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SynchronizedWithTransactionalService implements StockManagementService {

    private final NormalStockRepository normalStockRepository;

    public SynchronizedWithTransactionalService(NormalStockRepository normalStockRepository) {
        this.normalStockRepository = normalStockRepository;
    }

    @Transactional
    public synchronized void deduction(String productName) {
        Stock stock = normalStockRepository.findByProductName(productName);

        stock.decreaseQuantity();
    }


}
