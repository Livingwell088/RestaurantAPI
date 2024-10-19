package com.example.Restaurant.Controller;

import com.example.Restaurant.Service.CartService;
import com.example.Restaurant.model.Cart;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(path = "/carts")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {


    @Autowired
    private CartService cartService;

    @GetMapping(path = "/getCarts")
    public List<Cart> getAllCarts(){

        return cartService.getAllCarts();
    }

    @PostMapping(path = "/add")
//    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(@RequestBody Cart cart) {
        return cartService.saveOrUpdate(cart).getId();
    }

    @PutMapping(path = "/{cartId}")
    public void editOrder(@PathVariable Long cartId, @RequestBody Cart cart) {
        if (!cartId.equals(cart.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "cartId in request URL and cartId in object body do not match");
        }
        cartService.update(cart);
    }

    @GetMapping(path = "/{id}")
    public Cart getOrder(@PathVariable Long id) {
        return cartService.getById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Error: Project with id %d not found.", id)));
    }


    @DeleteMapping(path ="/{cartId}")
    public void deleteCarts(@PathVariable Long cartId) {
        cartService.deleteCarts(cartId);
    }

    @DeleteMapping()
    public void deleteAll() {
        cartService.deleteAll();
    }

//    @GetMapping(path = "/{id}")
//    public Carts getOrder(@PathVariable Long id) {
//        return cartService.getById(id).orElseThrow(() -> new EntityNotFoundException(
//                String.format("Error: Project with id %d not found.", id)));
//    }

}
