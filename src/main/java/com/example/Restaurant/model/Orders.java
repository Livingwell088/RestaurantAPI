package com.example.Restaurant.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;


@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;


    private String orderName;
    private String orderPrice;
    private String orderAmount;

    private String quantity;


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu item;

    public Orders(Long id, String orderName, String orderPrice, String orderAmount, String quantity, Menu item) {
        this.id = id;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderAmount = orderAmount;
        this.quantity = quantity;
        this.item = item;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public Menu getItem() {
        return item;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", quantity='" + quantity + '\'' +
                ", item=" + item +
                '}';
    }
}


