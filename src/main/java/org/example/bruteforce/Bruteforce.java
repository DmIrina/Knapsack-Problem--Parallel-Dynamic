package org.example.bruteforce;

import org.example.helpers.PackBackpack;
import org.example.models.ItemsWarehouse;

public class Bruteforce implements PackBackpack {
    private ItemsWarehouse itemsWarehouse;
    private int countItems;
    private int maxW;           // max weight of knapsack
    int totalPrice = 0;
    int totalWight = 0;
    long maxSelection = 0;

    public Bruteforce(ItemsWarehouse itemsWarehouse, int maxW) {
        this.itemsWarehouse = itemsWarehouse;
        countItems = itemsWarehouse.getItems().size();
        this.maxW = maxW;
    }

    @Override
    public void pack() {
        long startTime = System.currentTimeMillis();
        long countCombination = (long) Math.pow(2, countItems);

        // пошук максимуму перебором
        // біти selection показують, чи присутній предмет у рюкзаку
        // (1 - беремо, 0 - ні:  00011 - беремо 2 предмета справа)
        for (long selection = 0; selection < countCombination; selection++) {
            int price = statePrice(selection);
            int wight = stateWeight(selection);
            if (wight <= maxW) {
                if (totalPrice < price) {
                    totalPrice = price;
                    totalWight = wight;
                    maxSelection = selection;
                }
            }
        }

        System.out.println("\nМетод перебору: використовуємо виключно для контролю на невеликих обсягах даних.");
        System.out.println("Зібраний рюкзак:");
        long numberOfBit = 1;
        for (int i = 0; i < countItems; i++) {
            if ((numberOfBit & maxSelection) > 0) {
                itemsWarehouse.getItem(i).printItem();
            }
            numberOfBit <<= 1;
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("countItems = %7d  capacity = %4d -  час виконання: %12d мс\n", countItems, maxW, executionTime);
        System.out.println("Вага = " + totalWight + " Вартість  = " + totalPrice);
    }

    // рахуємо вартість усіх обраних предметів для варіанту state
    // (selection = 3 = 00011 - вказує на 2 останні предмети)
    private int statePrice(long selection) {
        long numberOfBit = 1;                           // перший (молодший) біт = перший предмет
        int price = 0;
        for (int i = 0; i < countItems; i++) {
            if ((numberOfBit & selection) != 0) {       //  поразрядної операції "І" -
                // кожен біт у результуючому виразі буде встановлений,
                // якщо він встановлений в обох операндах.
                price += itemsWarehouse.getPrice(i);
            }
            numberOfBit <<= 1;                          // зсув вліво на 1 біт - переходимо до наступного
        }
        return price;
    }

    private int stateWeight(long selection) {
        long numberOfBit = 1;
        int weight = 0;
        for (int i = 0; i < countItems; i++) {
            if ((numberOfBit & selection) != 0) {
                weight += itemsWarehouse.getWeight(i);
            }
            numberOfBit <<= 1;
        }
        return weight;
    }
}