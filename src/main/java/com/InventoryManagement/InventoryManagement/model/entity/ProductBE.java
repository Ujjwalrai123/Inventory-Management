package com.InventoryManagement.InventoryManagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Entity
@Table(name = "products")
public class ProductBE {

//    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas

    private String name;
    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas

    private String description;
    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas

    private double price;
    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas

    private int quantity;


    public ProductBE(){

    }

    public ProductBE(Long id, String name, String description, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
