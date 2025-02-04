package com.example.Restaurant.Service;

import com.example.Restaurant.Repository.CartItemRepository;
import com.example.Restaurant.Repository.CartRepository;
import com.example.Restaurant.Repository.OrderRepository;
import com.example.Restaurant.Repository.UserRepository;
import com.example.Restaurant.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Orders placeOrder(Orders orders) {

//        Clear the cart
//        Add items to order
//        make items placed

        String userId = orders.getUser().getUsernameId();
        Cart currentCart = cartRepository.findById(userId).get();
        currentCart.setCartItems(new ArrayList<>());
        cartRepository.saveAndFlush(currentCart);

        List<CartItem> orderItems = orders.getItems();

        for (CartItem item : orderItems){
            item.setOrderPlaced(true);
            cartItemRepository.saveAndFlush(item);
        }

//        System.out.println("orderItems: " + orderItems.toString());
//        System.out.println("Order.getItems(): " + orders.getItems().toString());

        System.out.println(orders);

        orderRepository.saveAndFlush(orders);
        return new Orders();
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getOrdersByUser(String userId) {

        if (userRepository.findById(userId).isEmpty()){
            return new ArrayList<>();
        }
        else{
            StoreUser currentUser = userRepository.findById(userId).get();
            return orderRepository.findAllByUser(currentUser);
        }

    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }


}
