package org.example.models;

import org.example.models.ItemsWarehouse;

import java.util.ArrayList;

public class KnapSack {
    private int totalPrice;

    private int totalWight;

    private ArrayList<Integer> packedItems;
    private int capacity;


    public KnapSack(int capacity, int totalPrice, int totalWight, ArrayList<Integer> packedItems) {
        this.capacity = capacity;
        this.totalWight = totalWight;
        this.totalPrice = totalPrice;
        this.packedItems = packedItems;
    }


    public void printResult(ItemsWarehouse itemsWarehouse) {
        System.out.println("Зібраний рюкзак:");
//        Collections.reverse(result);
        for (int num : packedItems) {
            itemsWarehouse.getItem(num).printItem();
        }
        System.out.println("Вага = " + totalWight + " Вартість  = " + totalPrice);
    }
}
