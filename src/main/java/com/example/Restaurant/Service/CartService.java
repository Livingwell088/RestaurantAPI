package com.example.Restaurant.Service;

import com.example.Restaurant.model.Cart;
import com.example.Restaurant.model.Menu;

import java.util.List;
import java.util.Optional;

public interface CartService {


    public List<Cart> getAllCarts();

    public Cart saveOrUpdate(Cart cart);

    Cart update(Cart cart);

    Optional<Cart> getById(Long id);

    void deleteCarts(Long id);

    boolean existByItem(Menu menu, String instructions);
}
