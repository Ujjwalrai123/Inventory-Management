package com.InventoryManagement.InventoryManagement.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BillBE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private LocalDateTime billDate;
    private Double totalAmount;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillItemBE> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBE user;

    public UserBE getUser() {
        return user;
    }

    public void setUser(UserBE user) {
        this.user = user;
    }

// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BillItemBE> getItems() {
        return items;
    }

    public void setItems(List<BillItemBE> items) {
        this.items = items;
    }
}
