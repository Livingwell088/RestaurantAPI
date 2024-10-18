package com.example.Restaurant.Repository;

import com.example.Restaurant.model.Menu;
import com.example.Restaurant.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Cart, Long> {

}
