package com.InventoryManagement.InventoryManagement.repository;

// import com.InventoryManagement.InventoryManagement.model.entity.ProductBE;
import com.InventoryManagement.InventoryManagement.model.entity.UserBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBE,Long> {
    Optional<UserBE> findByEmail(String email);
    Optional<UserBE> findByUserName(String userName);
}
