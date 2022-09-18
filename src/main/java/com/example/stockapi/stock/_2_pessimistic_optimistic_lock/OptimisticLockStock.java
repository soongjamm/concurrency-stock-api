package com.example.stockapi.stock._2_pessimistic_optimistic_lock;

import javax.persistence.*;

@Entity
public class OptimisticLockStock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productName;

    private long quantity;

    @Version
    private long version;

    protected OptimisticLockStock() {
    }

    public OptimisticLockStock(String productName, long quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public long getQuantity() {
        return quantity;
    }
}
