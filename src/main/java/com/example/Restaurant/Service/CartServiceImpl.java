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
    public List<CartItem> getAllCarts(String sessionId) {

        Optional<Cart> cart = cartRepository.findById(sessionId);

        if (cart.isPresent()){
            return cart.get().getCartItems();
        }
        else{
            return new ArrayList<>();
        }
//        return cartRepository.
    }

    @Override
    public Cart addToCart(CartItem item) {

        Optional<Cart> cart = cartRepository.findById(item.getCartId());

        if (cart.isPresent()){
            System.out.println("Found");
        }
        else{
            Cart current = new Cart();
            current.setCart_Id(item.getCartId());
            cartRepository.saveAndFlush(current);
            System.out.println("Make One!");
        }

        cart = cartRepository.findById(item.getCartId());


        Long exist = cartItemRepository.findAllByItemAndSpecialInstruction(item.getCartId(), item.getItem(), item.getSpecialInstruction());

        if (exist != null){
            System.out.println("Found Duplicate: " + cartItemRepository.findById(exist));

            CartItem currentItem = cartItemRepository.findById(exist).get();
            currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
            currentItem.setOrderPrice(Double.parseDouble(currentItem.getItem().getPrice()) * currentItem.getQuantity());

            cartItemRepository.saveAndFlush(currentItem);
        }
        else{
            cartItemRepository.saveAndFlush(item);
        }


        List<CartItem> items = cartItemRepository.findAllByCartId(item.getCartId());

        System.out.println("Cart:" + cart.get().toString());
        System.out.println("Cart Items: " + items);


        Cart newCart = cart.get();
        newCart.setCartItems(items);


        cartRepository.saveAndFlush(newCart);


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
    public Cart update(CartItem item) {

        System.out.println("EDIT");

//        Optional<CartItem> currentItem = cartItemRepository.findById(item.getId());
//        CartItem current = currentItem.get();
//
//        current = item;

        cartItemRepository.saveAndFlush(item);
        List<CartItem> items = cartItemRepository.findAllByCartId(item.getCartId());

        System.out.println(items);


        Optional<Cart> cart = cartRepository.findById(item.getCartId());
        cart = cartRepository.findById(item.getCartId());

        Cart newCart = cart.get();
        newCart.setCartItems(items);

        System.out.println(newCart.getCartItems());



        cartRepository.saveAndFlush(newCart);
        return new Cart();
    }

    public void deleteCarts(CartItem item) throws RuntimeException {

        System.out.println("Deleting");
        System.out.println(item.toString());

        String cartId = item.getCartId();
        Cart cart = getById(cartId).orElseThrow(() -> new EntityNotFoundException(
                String.format("Carts with id %d not found.", cartId)));

        Long currentId = cartItemRepository.findAllByItemAndSpecialInstruction(cartId, item.getItem(), item.getSpecialInstruction());
        CartItem current = cartItemRepository.getById(currentId);

        if (current.getQuantity() == 1){
            cart.getCartItems().remove(current);
            cartItemRepository.deleteById(currentId);

        }
        else{
            current.setQuantity(current.getQuantity() - 1);
            current.setOrderPrice(current.getQuantity() * Double.parseDouble(current.getItem().getPrice()));

            cartItemRepository.flush();
//        cartRepository.deleteById(cartId);
        }


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
