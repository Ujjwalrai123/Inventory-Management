package com.InventoryManagement.InventoryManagement.service;

import com.InventoryManagement.InventoryManagement.model.entity.ProductBE;

import java.util.List;

public interface IProductService{

    ProductBE addProduct(com.InventoryManagement.InventoryManagement.model.request.ProductRequest productRequest);
    List<ProductBE> getAllProduct();
    ProductBE getProductById(Long id);
    ProductBE updateProduct(Long id, com.InventoryManagement.InventoryManagement.model.request.ProductRequest productRequest);
    void deleteProduct(Long id);
}
