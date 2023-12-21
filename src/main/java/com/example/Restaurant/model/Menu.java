package com.example.Restaurant.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Menu {

    @Id
    private long id;

    private String name, size, category, price, spicy;

    public Menu() {
    }

    public Menu(long id, String name, String size, String category, String price, String spicy) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.category = category;
        this.price = price;
        this.spicy = spicy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", spicy='" + spicy + '\'' +
                '}';
    }
}

