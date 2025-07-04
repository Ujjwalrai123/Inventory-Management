package com.InventoryManagement.InventoryManagement.service.impl;

import com.InventoryManagement.InventoryManagement.exception.ProductCustomException;
import com.InventoryManagement.InventoryManagement.model.entity.ProductBE;
import com.InventoryManagement.InventoryManagement.model.request.ProductRequest;
import com.InventoryManagement.InventoryManagement.repository.ProductRepository;
import com.InventoryManagement.InventoryManagement.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.InventoryManagement.InventoryManagement.constants.InventoryConstants.PRODUCT_NOT_FOUND;

@Service
public class ProductServiceIMPL implements IProductService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductBE addProduct(ProductRequest productRequest) {
       ProductBE productBE = modelMapper.map(productRequest,ProductBE.class);
       return productRepository.save(productBE);
    }
    @Override
    public List<ProductBE> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductBE getProductById(Long id) {
        Optional<ProductBE> productBE = productRepository.findById(id);
        if(productBE.isEmpty()){
            throw new ProductCustomException(PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return productBE.get();
    }
    @Override
    public ProductBE updateProduct(Long id, com.InventoryManagement.InventoryManagement.model.request.ProductRequest productRequest) {
       //handling error
        ProductBE productBE = modelMapper.map(productRequest, ProductBE.class);
        productBE.setId(id);
        return productRepository.save(productBE);
    }
    @Override
    public void deleteProduct(Long id) {
        //handling error
        Optional<ProductBE> productBE = productRepository.findById(id);
        if (productBE.isPresent()){
            productRepository.deleteById(id);
        }
    }
}
