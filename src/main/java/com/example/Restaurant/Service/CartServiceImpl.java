package com.example.Restaurant.Service;

import com.example.Restaurant.Repository.CartRepository;
import com.example.Restaurant.model.Cart;
import com.example.Restaurant.model.Menu;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService{


    @Autowired
    private CartRepository cartRepository;

//    @Autowired
//    public OrderServiceImpl(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart saveOrUpdate(Cart cart){

        if (cartRepository.existsByItemAndSpecialInstruction(cart.getItem(), cart.getSpecialInstruction())){
            System.out.println("Duplicate");
//            System.out.println(cartRepository.findAllByItemAndSpecialInstruction(carts.getItem(), carts.getSpecialInstruction()));

            Optional<Cart> current = getById(cartRepository.findAllByItemAndSpecialInstruction(cart.getItem(), cart.getSpecialInstruction()));
            Cart old = null;
            if (current.isPresent()){
                old = current.get();

                old.setQuantity(old.getQuantity() + cart.getQuantity());
                old.setOrderPrice(old.getOrderPrice() + cart.getOrderPrice());

                System.out.println(old);
            }
            assert old != null;
            return update(old);

        }
        else{
            return cartRepository.save(cart);
        }

//        return carts;
    }

    public Optional<Cart> getById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart update(Cart cart) {
        if (cart.getId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cannot update a cart with a null id.");
        }
        if (getById(cart.getId()).isEmpty()) {
            throw new EntityNotFoundException(String.format(
                    "Cannot update the cart with id %d because it does not exist.", cart.getId()));
        }
        return cartRepository.saveAndFlush(cart);
    }

    public void deleteCarts(Long cartId) throws RuntimeException {
        Cart cart = getById(cartId).orElseThrow(() -> new EntityNotFoundException(
                String.format("Carts with id %d not found.", cartId)));

        cartRepository.deleteById(cartId);
        cartRepository.flush();
    }

    public void deleteAll() {
        cartRepository.deleteAll();
        cartRepository.flush();
    }

    public boolean existByItem(Menu menu, String instructions){

        return cartRepository.existsByItemAndSpecialInstruction(menu, instructions);
    }
}
