package com.example.Restaurant.Service;

import com.example.Restaurant.model.Cart;
import com.example.Restaurant.model.CartItem;
import com.example.Restaurant.model.Menu;

import java.util.List;
import java.util.Optional;

public interface CartService {


    public List<CartItem> getAllCarts(String sessionId);

    public Cart addToCart(CartItem item);

    public Cart saveOrUpdate(Cart cart);

    Cart update(Cart cart);

    Optional<Cart> getById(String id);

    void deleteCarts(CartItem item);

    void deleteAll();

    boolean existByItem(Menu menu, String instructions);

    String generateCart();
}
