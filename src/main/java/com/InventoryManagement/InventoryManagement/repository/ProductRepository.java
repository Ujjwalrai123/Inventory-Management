package com.InventoryManagement.InventoryManagement.repository;

import com.InventoryManagement.InventoryManagement.model.entity.ProductBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductBE,Long>
{
    Optional<ProductBE> findByName(String name);

}
