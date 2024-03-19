package com.shoppersworld.OrderService.repository;

import com.shoppersworld.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<Order,Long> {
}
