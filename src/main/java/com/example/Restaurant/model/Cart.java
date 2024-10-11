package com.example.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    @Id
    private String id;


    private String orderName;
    private Float orderPrice;
    private Integer orderAmount;

//    @JsonManagedReference(value = "order-items")
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rateCard", cascade = CascadeType.ALL)
//    private List<Menu> items = new ArrayList<>();
}
