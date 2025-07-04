package com.InventoryManagement.InventoryManagement.model.request;

import jakarta.validation.constraints.*;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

public class ProductRequest {
    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas
    @NotBlank  // ye sirf string pe work karta hai, ensure karta hai string is not null aur whitespace bhi naa hoo nhi to validation fails
    @Size(min = 3, max=100) //ye String, collection, map, and arrays pe work karta hai aur ensure karta hai element count range me ho

    private String name;
    @NotNull
    @NotBlank
    @Size(min = 3, max=1000)
    private String description;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    @NotNull
    @Min(value = 0)
    @Digits(integer = 10, fraction = 0)
    private int quantity;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, Double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
