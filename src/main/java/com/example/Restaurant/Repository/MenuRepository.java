package com.example.Restaurant.Repository;

import com.example.Restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
