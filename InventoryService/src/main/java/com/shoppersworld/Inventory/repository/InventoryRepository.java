package com.shoppersworld.Inventory.repository;

import com.shoppersworld.Inventory.dto.InventoryResponse;
import com.shoppersworld.Inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

List<Inventory> findByProductIn(List<String> product);
}
