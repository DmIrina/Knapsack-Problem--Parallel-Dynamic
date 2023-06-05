package org.example.models;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private int weight;
    private int price;

    public Item(int id, String name, int weight, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public void printItem() {
        System.out.printf("%9d  %-45s %4d грн. %4d  кг\n", (getId() + 1), getName(), getPrice(), getWeight());
    }
}
