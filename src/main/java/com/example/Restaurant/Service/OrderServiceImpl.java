package com.example.Restaurant.Service;

import com.example.Restaurant.Repository.OrderRepository;
import com.example.Restaurant.model.Orders;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders saveOrUpdate(Orders orders){
        return orderRepository.save(orders);
    }

    public Optional<Orders> getById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders update(Orders orders) {
        if (orders.getId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot update a project with a null id.");
        }
        if (getById(orders.getId()).isEmpty()) {
            throw new EntityNotFoundException(String.format(
                    "Cannot update the project with id %d because it does not exist.", orders.getId()));
        }
        return orderRepository.saveAndFlush(orders);
    }}
