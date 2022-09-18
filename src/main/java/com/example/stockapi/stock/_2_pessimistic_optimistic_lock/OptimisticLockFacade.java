package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockFacade {

    private static final Logger LOG = LoggerFactory.getLogger(OptimisticLockFacade.class);
    private final OptimisticLockService stockService;

    public OptimisticLockFacade(OptimisticLockService stockService) {
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
