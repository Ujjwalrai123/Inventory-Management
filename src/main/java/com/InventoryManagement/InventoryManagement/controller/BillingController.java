package com.InventoryManagement.InventoryManagement.controller;

import com.InventoryManagement.InventoryManagement.model.entity.BillBE;
import com.InventoryManagement.InventoryManagement.model.request.BillingRequest;
import com.InventoryManagement.InventoryManagement.model.response.BillingResponse;
import com.InventoryManagement.InventoryManagement.repository.BillRepository;
import com.InventoryManagement.InventoryManagement.service.IBillingService;
import com.InventoryManagement.InventoryManagement.util.PdfGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private IBillingService billingService;

    @Autowired
    private BillRepository billRepository;

    @PostMapping("/generate")
    public ResponseEntity<BillingResponse> generateBill(@RequestBody BillingRequest request) {
        return ResponseEntity.ok(billingService.generateBill(request));
    }

    @GetMapping("/invoice/{billId}")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long billId) throws IOException {
        BillBE bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        ByteArrayInputStream pdf = PdfGenerator.generateInvoice(bill);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoice_" + billId + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }
}
