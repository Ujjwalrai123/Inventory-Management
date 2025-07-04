package com.InventoryManagement.InventoryManagement.service.impl;

import com.InventoryManagement.InventoryManagement.exception.UserCustomException;
import com.InventoryManagement.InventoryManagement.model.entity.UserBE;
import com.InventoryManagement.InventoryManagement.model.request.UserRequest;
import com.InventoryManagement.InventoryManagement.repository.UserRepository;
import com.InventoryManagement.InventoryManagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.InventoryManagement.InventoryManagement.constants.InventoryConstants.USER_NOT_FOUND;
import com.InventoryManagement.InventoryManagement.model.enums.Role;

@Service  // ✅ This is mandatory to make it a Spring-managed bean
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserBE addUser(UserRequest userRequest) {
        String encodedPassword=passwordEncoder.encode(userRequest.getPassword());
        UserBE userBE = modelMapper.map(userRequest, UserBE.class);
        //yha pe user login ka data save ho rha hai
        userBE.setPassword(encodedPassword);
        return userRepository.save(userBE);
    }

    @Override
    public List<UserBE> getALlUser() {
        return userRepository.findAll();
    }

    @Override
    public UserBE getUserById(Long id) {
        Optional<UserBE> userBE = userRepository.findById(id);
        if(userBE.isEmpty()){
            throw new UserCustomException(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return userBE.get();
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<UserBE> userBE = userRepository.findById(id);
        if (userBE.isEmpty()) {
            throw new UserCustomException(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean registerUser(UserRequest userRequest) {
        // Check if user already exists by username or email
        if (userRepository.findByUserName(userRequest.getUserName()).isPresent() ||
            userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return false;
        }
        UserBE user = new UserBE();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        String roleStr = userRequest.getRole();
        Role role = Role.ROLE_USER;
        if (roleStr != null && !roleStr.isEmpty()) {
            try {
                role = Role.valueOf(roleStr);
            } catch (IllegalArgumentException ignored) {}
        }
        user.setRole(role);
        userRepository.save(user);
        return true;
    }
}
