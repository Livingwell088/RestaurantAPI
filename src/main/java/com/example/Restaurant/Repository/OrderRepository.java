package com.example.Restaurant.Repository;

import com.example.Restaurant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
