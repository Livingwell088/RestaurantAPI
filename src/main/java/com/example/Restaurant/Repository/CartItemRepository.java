package com.example.Restaurant.Repository;
import com.example.Restaurant.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}