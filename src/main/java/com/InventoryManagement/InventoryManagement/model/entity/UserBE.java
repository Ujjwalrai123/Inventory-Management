package com.InventoryManagement.InventoryManagement.model.entity;

import com.InventoryManagement.InventoryManagement.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users") //users name ka table ban jayega database me jaha pe username,password sab kuch store ho jayega

public class UserBE {

//    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas

    //ye not null lagane se kaam nhi hoga hame controller me requestbody ke phle @valid lgaana hoga
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi ba
    private String userName;

    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas
    private String email;

    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas
    private String password;

    @NotNull //ye ensure karta hai ki field is not null kisi me object type pe work karta hai naaki string pe hi bas
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserBE() {
    }

    public UserBE(Long id, String userName, String email, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.userName = username;
    }
}
