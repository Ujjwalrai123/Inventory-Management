package com.InventoryManagement.InventoryManagement.model.entity;

import jakarta.persistence.*;

@Entity
public class BillItemBE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private double price;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private BillBE bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public BillBE getBill() {
        return bill;
    }

    public void setBill(BillBE bill) {
        this.bill = bill;
    }
// Getters and Setters
}
