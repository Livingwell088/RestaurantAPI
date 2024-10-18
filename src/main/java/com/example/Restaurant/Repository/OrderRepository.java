package com.example.Restaurant.Repository;

import com.example.Restaurant.model.Menu;
import com.example.Restaurant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    boolean existsByItemAndSpecialInstruction(Menu item, String specialInstruction);

    @Query("SELECT o.id FROM Orders o WHERE o.item = :item AND o.specialInstruction = :instruction")
    Long findAllByItemAndSpecialInstruction(@Param("item") Menu item, @Param("instruction") String instruction);
}
