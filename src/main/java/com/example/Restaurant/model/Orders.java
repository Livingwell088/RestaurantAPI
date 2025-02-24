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

    private String orderType;

    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private StoreUser user;


    private String phoneNumber;

    @OneToMany
    private List<CartItem> items;

    public Orders() {
    }

    public Orders(Long id, String orderName, double orderPrice, String orderType, String address, StoreUser user, String phoneNumber, List<CartItem> items) {
        this.id = id;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderType = orderType;
        this.address = address;
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StoreUser getUser() {
        return user;
    }

    public void setUser(StoreUser user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderType='" + orderType + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", items=" + items +
                '}';
    }
}


