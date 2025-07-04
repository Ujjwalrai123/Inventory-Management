package com.InventoryManagement.InventoryManagement.model.response;

import java.time.LocalDateTime;
import java.util.List;

public class BillingResponse {

    private Long billId;
    private String customerName;
    private LocalDateTime billDate;
    private Double totalAmount;
    private List<ItemDetail> items;
    private List<InventoryDetail> updatedInventory;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public List<ItemDetail> getItems() {
        return items;
    }

    public void setItems(List<ItemDetail> items) {
        this.items = items;
    }

    public List<InventoryDetail> getUpdatedInventory() {
        return updatedInventory;
    }

    public void setUpdatedInventory(List<InventoryDetail> updatedInventory) {
        this.updatedInventory = updatedInventory;
    }

    // Inner class for each item in the bill
    public static class ItemDetail {
        private String productName;
        private int quantity;
        private double price;
        private double subtotal;
        private int remainingQuantity;

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

        public int getRemainingQuantity() {
            return remainingQuantity;
        }

        public void setRemainingQuantity(int remainingQuantity) {
            this.remainingQuantity = remainingQuantity;
        }
    }

    // Inner class for showing updated inventory
    public static class InventoryDetail {
        private Long productId;
        private String name;
        private double price;
        private int quantity;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
