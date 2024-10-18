package com.example.Restaurant.Service;

import com.example.Restaurant.Repository.OrderRepository;
import com.example.Restaurant.model.Menu;
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

        if (orderRepository.existsByItemAndSpecialInstruction(orders.getItem(), orders.getSpecialInstruction())){
            System.out.println("Duplicate");
//            System.out.println(orderRepository.findAllByItemAndSpecialInstruction(orders.getItem(), orders.getSpecialInstruction()));

            Optional<Orders> current = getById(orderRepository.findAllByItemAndSpecialInstruction(orders.getItem(), orders.getSpecialInstruction()));
            Orders old = null;
            if (current.isPresent()){
                old = current.get();

                old.setQuantity(old.getQuantity() + orders.getQuantity());
                old.setOrderPrice(old.getOrderPrice() + orders.getOrderPrice());

                System.out.println(old);
            }
            assert old != null;
            return update(old);

        }
        else{
            return orderRepository.save(orders);
        }

//        return orders;
    }

    public Optional<Orders> getById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders update(Orders orders) {
        if (orders.getId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot update a order with a null id.");
        }
        if (getById(orders.getId()).isEmpty()) {
            throw new EntityNotFoundException(String.format(
                    "Cannot update the order with id %d because it does not exist.", orders.getId()));
        }
        return orderRepository.saveAndFlush(orders);
    }

    public void deleteOrders(Long orderId) throws RuntimeException {
        Orders order = getById(orderId).orElseThrow(() -> new EntityNotFoundException(
                String.format("Orders with id %d not found.", orderId)));

        orderRepository.deleteById(orderId);
        orderRepository.flush();
    }

    public boolean existByItem(Menu menu, String instructions){

        return orderRepository.existsByItemAndSpecialInstruction(menu, instructions);
    }

}
