package com.InventoryManagement.InventoryManagement.model.request;

import com.InventoryManagement.InventoryManagement.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas
    @NotBlank  // ye sirf string pe work karta hai, ensure karta hai string is not null aur whitespace bhi naa hoo nhi to validation fails
    @Size(min = 3, max=100) //ye String, collection, map, and arrays pe work karta hai aur ensure karta hai element count range me ho

    private String userName;
    @NotNull
    @Email
    @NotBlank
    @Size(min = 3, max = 50)

    private String email;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)

    private String password;
    @NotNull
    private String role;

    public UserRequest() {
    }

    public UserRequest(String userName, String email, String password, String role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return userName;
    }
}
