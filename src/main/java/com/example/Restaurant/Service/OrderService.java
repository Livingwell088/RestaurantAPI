package com.example.Restaurant.Service;


import com.example.Restaurant.model.Menu;
import com.example.Restaurant.model.Cart;
import com.example.Restaurant.model.Orders;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    public Orders saveOrders(Orders orders);

    public List<Orders> getAllOrders();

    public void deleteAll();
}
