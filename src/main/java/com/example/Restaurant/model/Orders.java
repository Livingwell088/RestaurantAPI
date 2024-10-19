package com.example.Restaurant.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;


@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String orderName;
    private double orderPrice;

    @OneToMany
    private List<Cart> orderItems;


    public Orders() {
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

    public List<Cart> getOrderItems() {
        return orderItems;
    }
}


