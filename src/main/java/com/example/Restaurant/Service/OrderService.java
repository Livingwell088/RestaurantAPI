package com.example.Restaurant.Service;


import com.example.Restaurant.model.Menu;
import com.example.Restaurant.model.Orders;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    public List<Orders> getAllOrders();

    public Orders saveOrUpdate(Orders orders);

    Orders update(Orders orders);

    Optional<Orders> getById(Long id);

    void deleteOrders(Long id);

    boolean existByItem(Menu menu, String instructions);
}
