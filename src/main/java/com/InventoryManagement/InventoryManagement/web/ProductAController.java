package com.InventoryManagement.InventoryManagement.web;

import com.InventoryManagement.InventoryManagement.model.entity.ProductBE;
import com.InventoryManagement.InventoryManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductAController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductBE());
        return "addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductBE product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<ProductBE> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "viewProducts";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductBE product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute ProductBE updatedProduct) {
        ProductBE product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            productRepository.save(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
} 