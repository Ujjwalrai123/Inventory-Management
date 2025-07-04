package com.InventoryManagement.InventoryManagement.service;

import com.InventoryManagement.InventoryManagement.model.entity.UserBE;
import com.InventoryManagement.InventoryManagement.model.request.UserRequest;

import java.util.List;

public interface UserService {
    UserBE addUser(UserRequest userRequest);
    List<UserBE> getALlUser();
    UserBE getUserById(Long id);         // Consistent Long
    boolean deleteUser(Long id);         // Fixed from String to Long
    boolean registerUser(UserRequest userRequest);
}
