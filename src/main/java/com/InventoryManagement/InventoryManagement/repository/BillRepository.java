package com.InventoryManagement.InventoryManagement.repository;

import com.InventoryManagement.InventoryManagement.model.entity.BillBE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillBE, Long> {
}
