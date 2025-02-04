package com.example.Restaurant.Controller;

import com.example.Restaurant.Service.CartService;
import com.example.Restaurant.Service.OrderService;
import com.example.Restaurant.model.Cart;
import com.example.Restaurant.model.Orders;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/getAllOrders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping(path = "/placeOrder")
    public Long placeOrder(@RequestBody Orders orders){

        System.out.println(orders.toString());
        System.out.println();

        orderService.placeOrder(orders);
        return 0L;
    }

    @PostMapping(path = "/deleteAll")
    public void deleteAll(){
        orderService.deleteAll();
    }

}
