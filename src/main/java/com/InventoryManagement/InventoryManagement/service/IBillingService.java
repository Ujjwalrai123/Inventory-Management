package com.InventoryManagement.InventoryManagement.service;

import com.InventoryManagement.InventoryManagement.model.request.BillingRequest;
import com.InventoryManagement.InventoryManagement.model.response.BillingResponse;

public interface IBillingService {
    BillingResponse generateBill(BillingRequest request);
}
