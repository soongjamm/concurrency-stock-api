package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import com.example.stockapi.stock.StockFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockStockFacade implements StockFacade {

    private static final Logger LOG = LoggerFactory.getLogger(OptimisticLockStockFacade.class);
    private final OptimisticLockStockService stockService;

    public OptimisticLockStockFacade(OptimisticLockStockService stockService) {
        this.stockService = stockService;
    }

    public void deduction(String productName) {
        while(true) {
            try {
                stockService.deduction(productName);
                break;
            } catch (Exception e) {
                LOG.warn("Optimistic Lock Version Exception. | {}", e.getMessage());
            }
        }
    }
}
