package com.InventoryManagement.InventoryManagement.web;

import com.InventoryManagement.InventoryManagement.model.entity.BillBE;
import com.InventoryManagement.InventoryManagement.model.entity.BillItemBE;
import com.InventoryManagement.InventoryManagement.model.entity.ProductBE;
import com.InventoryManagement.InventoryManagement.repository.BillRepository;
import com.InventoryManagement.InventoryManagement.repository.ProductRepository;
import com.InventoryManagement.InventoryManagement.repository.UserRepository;
import com.InventoryManagement.InventoryManagement.model.entity.UserBE;
import com.InventoryManagement.InventoryManagement.util.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BillingAController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/billing")
    public String showBillingForm(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "billing";
    }

    @PostMapping("/billing")
    public String generateBill(String customerName, Long productId, int quantity) {
        ProductBE product = productRepository.findById(productId).orElse(null);
        if (product != null && quantity > 0 && product.getQuantity() >= quantity) {
            BillBE bill = new BillBE();
            bill.setCustomerName(customerName);
            bill.setTotalAmount(product.getPrice() * quantity);
            bill.setBillDate(LocalDateTime.now());

            // Set the current user on the bill
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            UserBE currentUser = userRepository.findByEmail(email).orElse(null);
            bill.setUser(currentUser);

            BillItemBE billItem = new BillItemBE();
            billItem.setProductName(product.getName());
            billItem.setQuantity(quantity);
            billItem.setPrice(product.getPrice());
            billItem.setSubtotal(product.getPrice() * quantity);
            billItem.setBill(bill);

            List<BillItemBE> items = new ArrayList<>();
            items.add(billItem);
            bill.setItems(items);

            billRepository.save(bill);
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }
        return "redirect:/bills";
    }

    @GetMapping("/bills")
    public String viewBills(Model model) {
        List<BillBE> bills = billRepository.findAll();
        model.addAttribute("bills", bills);
        return "viewBills";
    }

    @GetMapping("/bills/pdf/{id}")
    public void downloadBillPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        BillBE bill = billRepository.findById(id).orElse(null);
        if (bill != null) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=bill-" + id + ".pdf");
            try (var in = PdfGenerator.generateInvoice(bill); var out = response.getOutputStream()) {
                in.transferTo(out);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Bill not found");
        }
    }
} 