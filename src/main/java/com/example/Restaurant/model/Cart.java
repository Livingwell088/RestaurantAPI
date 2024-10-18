package com.example.Restaurant.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;


@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;


    private String orderName;
    private double orderPrice;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu item;

    private String specialInstruction;

    public Cart(Long id, String orderName, double orderPrice, int quantity, Menu item, String specialInstruction) {
        this.id = id;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
        this.item = item;
        this.specialInstruction = specialInstruction;
    }

    public Cart() {
    }


    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Menu getItem() {
        return item;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setItem(Menu item) {
        this.item = item;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", quantity=" + quantity +
                ", item=" + item +
                ", specialInstruction='" + specialInstruction + '\'' +
                '}';
    }
}


