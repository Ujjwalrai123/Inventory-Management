package com.InventoryManagement.InventoryManagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.InventoryManagement.InventoryManagement.model.request.UserRequest;
import com.InventoryManagement.InventoryManagement.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute UserRequest userRequest, Model model) {
        boolean success = userService.registerUser(userRequest);
        if (success) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Registration failed. Try again.");
            return "register";
        }
    }
} 