package org.example.helpers;

import org.example.models.ItemsWarehouse;
import org.example.models.KnapSack;

import java.util.ArrayList;
import java.util.Collections;

public class Helper {

    // знайти список вибраних предметів
    public static KnapSack findSelectedItems(int[][] dt, ItemsWarehouse itemsWarehouse, int capacity) {
        int totalPrice = 0;
        int totalWight = 0;
        ArrayList<Integer> packedItems = new ArrayList<>();
        int n = itemsWarehouse.getItems().size();
        while (n > 0 && capacity > 0) {
            if (dt[n][capacity] != dt[n - 1][capacity]) {
                packedItems.add(n - 1);          // Додавання індексу вибраного предмета
                totalPrice += itemsWarehouse.getPrice(n - 1);
                totalWight += itemsWarehouse.getWeight(n - 1);
                capacity -= itemsWarehouse.getWeight(n - 1);
            }
            n--;
        }
        Collections.reverse(packedItems);
        return new KnapSack(capacity, totalPrice, totalWight, packedItems);
    }
}
