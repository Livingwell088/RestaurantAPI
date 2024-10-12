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
    private Double orderPrice;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu item;

    private String specialInstruction;

    public Orders(Long id, String orderName, Double orderPrice, Integer quantity, Menu item, String specialInstruction) {
        this.id = id;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
        this.item = item;
        this.specialInstruction = specialInstruction;
    }

    public Orders() {
    }


    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Menu getItem() {
        return item;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
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


