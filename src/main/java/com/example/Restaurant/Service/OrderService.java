package com.example.Restaurant.Service;


import com.example.Restaurant.model.Orders;

import java.util.List;


public interface OrderService {

    public Orders placeOrder(Orders orders);

    public List<Orders> getAllOrders();

    public List<Orders> getOrdersByUser(String userId);

    public void deleteAll();
}
