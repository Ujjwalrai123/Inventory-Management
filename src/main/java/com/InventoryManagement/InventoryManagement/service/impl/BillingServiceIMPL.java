package com.InventoryManagement.InventoryManagement.service.impl;

import com.InventoryManagement.InventoryManagement.model.entity.*;
import com.InventoryManagement.InventoryManagement.model.request.BillingRequest;
import com.InventoryManagement.InventoryManagement.model.response.BillingResponse;
import com.InventoryManagement.InventoryManagement.repository.BillRepository;
import com.InventoryManagement.InventoryManagement.repository.ProductRepository;
import com.InventoryManagement.InventoryManagement.repository.UserRepository;
import com.InventoryManagement.InventoryManagement.service.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillingServiceIMPL implements IBillingService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BillingResponse generateBill(BillingRequest request) {
        // Get user
        UserBE user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Prepare new bill
        BillBE bill = new BillBE();
        bill.setCustomerName(request.getCustomerName());
        bill.setBillDate(LocalDateTime.now());
        bill.setUser(user);

        List<BillItemBE> billItems = new ArrayList<>();
        double total = 0;

        // Process each item in request
        for (BillingRequest.ItemRequest itemReq : request.getItems()) {
            ProductBE product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName() +
                        ". Available: " + product.getQuantity() + ", Requested: " + itemReq.getQuantity());
            }

            // Update stock
            product.setQuantity(product.getQuantity() - itemReq.getQuantity());
            productRepository.save(product);

            // Create bill item
            BillItemBE item = new BillItemBE();
            item.setProductName(product.getName());
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice());
            item.setSubtotal(item.getQuantity() * item.getPrice());
            item.setBill(bill);
            billItems.add(item);

            total += item.getSubtotal();
        }

        // Finalize and save bill
        bill.setItems(billItems);
        bill.setTotalAmount(total);
        billRepository.save(bill);

        // Save items again after bill ID is generated
        for (BillItemBE item : billItems) {
            item.setBill(bill);
        }
        billRepository.save(bill);

        // Build billing response
        BillingResponse response = new BillingResponse();
        response.setBillId(bill.getId());
        response.setCustomerName(bill.getCustomerName());
        response.setBillDate(bill.getBillDate());
        response.setTotalAmount(bill.getTotalAmount());

        // Prepare item details
        List<BillingResponse.ItemDetail> responseItems = new ArrayList<>();
        for (BillItemBE item : billItems) {
            BillingResponse.ItemDetail detail = new BillingResponse.ItemDetail();
            detail.setProductName(item.getProductName());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getPrice());
            detail.setSubtotal(item.getSubtotal());

            // Add remaining quantity
            ProductBE p = productRepository.findByName(item.getProductName()).orElse(null);
            if (p != null) {
                detail.setRemainingQuantity(p.getQuantity());
            }

            responseItems.add(detail);
        }
        response.setItems(responseItems);

        // Add full updated inventory
        List<ProductBE> allProducts = productRepository.findAll();
        List<BillingResponse.InventoryDetail> inventoryList = new ArrayList<>();
        for (ProductBE p : allProducts) {
            BillingResponse.InventoryDetail inv = new BillingResponse.InventoryDetail();
            inv.setProductId(p.getId());
            inv.setName(p.getName());
            inv.setPrice(p.getPrice());
            inv.setQuantity(p.getQuantity());
            inventoryList.add(inv);
        }
        response.setUpdatedInventory(inventoryList);

        return response;
    }
}
