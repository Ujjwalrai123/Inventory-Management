package com.InventoryManagement.InventoryManagement.controller;

import com.InventoryManagement.InventoryManagement.model.entity.UserBE;
import com.InventoryManagement.InventoryManagement.model.request.UserRequest;
import com.InventoryManagement.InventoryManagement.model.response.ErrorResponse;
import com.InventoryManagement.InventoryManagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class AuthController {
    @Autowired
    private UserService userService;

//    @Operation(
//            summary="Register a new user", responses = "200",description = "user succesfully created", content =
//    @Content(mediaType = "application/jason",schema = @Schema(implementation = UserBE.class))
//    ),
@Operation(
        summary = "Register a new user",
        description = "User successfully created",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User created successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserBE.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "User Already Present",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
)

    @PostMapping("/register")
    public ResponseEntity<UserBE> addUser(@Valid @RequestBody UserRequest userRequest){
        UserBE userBE = userService.addUser(userRequest);
        return new ResponseEntity<>(userBE, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<List<UserBE>> getAlluser(){
        List<UserBE> aLlUser = userService.getALlUser();
        return new ResponseEntity<>(aLlUser,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserBE> getUserById(@PathVariable String id){
        UserBE userById = userService.getUserById(Long.valueOf(id));
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        boolean user = userService.deleteUser(Long.valueOf(id));
        return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
    }
}
