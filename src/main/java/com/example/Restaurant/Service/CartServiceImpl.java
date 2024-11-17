package com.example.Restaurant.Service;

//import com.example.Restaurant.Repository.CartRepository;
import com.example.Restaurant.Repository.CartItemRepository;
import com.example.Restaurant.Repository.CartRepository;
import com.example.Restaurant.model.Cart;
import com.example.Restaurant.model.CartItem;
import com.example.Restaurant.model.Menu;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CartServiceImpl implements CartService{


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

//    @Autowired
//    public OrderServiceImpl(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }

    @Override
    public Cart getAllCarts(String sessionId) {

        Optional<Cart> cart = cartRepository.findById(sessionId);
        return cart.orElse(null);
//        return cartRepository.
    }

    @Override
    public Cart addToCart(CartItem item) {

        Optional<Cart> cart = cartRepository.findById(item.getCartId());

        cartItemRepository.saveAndFlush(item);
//
//        Cart current;
//        if (cart.isPresent()){
//            current = cart.get();
//
//            List<CartItem> items = current.getCartItems();
//
//            items.add(item);
//            current.setCartItems(items);
//
//        }
//        else{
//            current = new Cart();
//            current.setCart_Id(item.getCartId());
//
//            List<CartItem> items = new ArrayList<>();
//            items.add(item);
//
//            current.setCartItems(items);
//        }
//        System.out.println(current.toString());
//
//        cartRepository.saveAndFlush(current);
        return new Cart();
    }

    @Override
    public Cart saveOrUpdate(Cart cart){

        return cart;

//        if (cartRepository.existsByItemAndSpecialInstruction(cart.getItem(), cart.getSpecialInstruction())){
//            System.out.println("Duplicate");
////            System.out.println(cartRepository.findAllByItemAndSpecialInstruction(carts.getItem(), carts.getSpecialInstruction()));
//
//            Optional<Cart> current = getById(cartRepository.findAllByItemAndSpecialInstruction(cart.getItem(), cart.getSpecialInstruction()));
//            Cart old = null;
//            if (current.isPresent()){
//                old = current.get();
//
//                old.setQuantity(old.getQuantity() + cart.getQuantity());
//                old.setOrderPrice(old.getOrderPrice() + cart.getOrderPrice());
//
//                System.out.println(old);
//            }
//            assert old != null;
//            return update(old);
//
//        }
//        else{
//            return cartRepository.save(cart);
//        }

//        return carts;
    }

    public Optional<Cart> getById(String id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart update(Cart cart) {
//        if (cart.getId() == null) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    "Cannot update a cart with a null id.");
//        }
        if (getById(cart.getCart_Id()).isEmpty()) {
            throw new EntityNotFoundException(String.format(
                    "Cannot update the cart with id %d because it does not exist.", cart.getCart_Id()));
        }
        return cartRepository.saveAndFlush(cart);
    }

    public void deleteCarts(String cartId) throws RuntimeException {
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

//        return cartRepository.existsByItemAndSpecialInstruction(menu, instructions);
        return true;
    }

    @Override
    public String generateCart() {

        Random rand = new Random();
        return "Guest" + String.valueOf(rand.nextInt());
    }


}
