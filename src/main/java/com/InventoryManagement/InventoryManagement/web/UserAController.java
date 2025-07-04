package com.InventoryManagement.InventoryManagement.web;

import com.InventoryManagement.InventoryManagement.model.entity.UserBE;
import com.InventoryManagement.InventoryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserAController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/view")
    public String viewUsers(Model model) {
        List<UserBE> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "viewUsers";
    }
} 