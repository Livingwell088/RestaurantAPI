package com.example.Restaurant.Controller;

import com.example.Restaurant.Service.OrderService;
import com.example.Restaurant.model.Menu;
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

    @GetMapping(path = "/getOrders")
    public List<Orders> getAllOrders(){

        return orderService.getAllOrders();
    }

    @PostMapping(path = "/add")
//    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(@RequestBody Orders orders) {
        return orderService.saveOrUpdate(orders).getId();
    }

    @PutMapping(path = "/{orderId}")
    public void editOrder(@PathVariable Long orderId, @RequestBody Orders orders) {
        if (!orderId.equals(orders.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "orderId in request URL and orderId in object body do not match");
        }
        orderService.update(orders);
    }

    @GetMapping(path = "/{id}")
    public Orders getOrder(@PathVariable Long id) {
        return orderService.getById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Error: Project with id %d not found.", id)));
    }


    @DeleteMapping(path ="/{orderId}")
    public void deleteOrders(@PathVariable Long orderId) {
        orderService.deleteOrders(orderId);
    }



}
