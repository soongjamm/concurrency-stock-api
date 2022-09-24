package com.example.stockapi.stock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productName;

    private long quantity;

    protected Stock() {
    }

    public Stock(String productName, long quantity) {
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
