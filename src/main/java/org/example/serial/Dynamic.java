package org.example.serial;

import org.example.helpers.Helper;
import org.example.helpers.PackBackpack;
import org.example.models.ItemsWarehouse;
import org.example.models.KnapSack;

public class Dynamic implements PackBackpack {
    private ItemsWarehouse itemsWarehouse;
    private int countItems;
    private int capacity;           // max weight of knapsack

    public Dynamic(ItemsWarehouse itemsWarehouse, int capacity) {
        this.itemsWarehouse = itemsWarehouse;
        countItems = itemsWarehouse.getItems().size();
        this.capacity = capacity;
    }

    @Override
    public void pack() {
        // System.out.println("\nПослідовна реалізація алгоритму пакування рюкзака методом динамічного програмування:");
        long startTime = System.currentTimeMillis();

        int[][] dt = new int[countItems + 1][capacity + 1];         // таблиця динамічного програмування

        // Заповнення таблиці A для знаходження оптимального рішення
        for (int i = 1; i <= countItems; i++) {                 // i - номер предмета
            for (int j = 1; j <= capacity; j++) {                   // j - місткості рюкзака
                if (itemsWarehouse.getWeight(i - 1) <= j) { // Вага предмета менша за вміщувану в рюкзак
                    // Вартість включення поточного предмета
                    int includePrice = itemsWarehouse.getPrice(i - 1) + dt[i - 1][j - itemsWarehouse.getWeight(i - 1)];
                    int excludePrice = dt[i - 1][j];             // Вартість виключення поточного предмета
                    if (includePrice > excludePrice) {
                        dt[i][j] = includePrice;                 // Вибір включення предмета
                    } else {
                        dt[i][j] = excludePrice;                 // Вибір виключення предмета
                    }
                } else {
                    dt[i][j] = dt[i - 1][j];       // Вага предмета більша за вміщувану ємність, пропускаємо предмет
                }
            }
        }

        KnapSack knapSack = Helper.findSelectedItems(dt, itemsWarehouse, capacity);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.printf(" Послідовна                %7d          %4d          %12d\n", countItems, capacity, executionTime);
        // knapSack.printResult(itemsWarehouse);
    }
}
