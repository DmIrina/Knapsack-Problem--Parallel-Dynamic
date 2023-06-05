package org.example.parallel;

import org.example.helpers.Helper;
import org.example.helpers.PackBackpack;
import org.example.models.ItemsWarehouse;
import org.example.models.KnapSack;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelDynamic implements PackBackpack {

    private ItemsWarehouse itemsWarehouse;  // Сховище предметів
    private int countItems;                 // Кількість предметів
    private int capacity;                   // Максимальна вага рюкзака

    private int numThreads = 1;             // Кількість потоків за замовчуванням

    public ParallelDynamic(ItemsWarehouse itemsWarehouse, int capacity, int numThreads) {
        this.itemsWarehouse = itemsWarehouse;
        this.countItems = itemsWarehouse.getItems().size();
        this.capacity = capacity;
        this.numThreads = numThreads;
    }

    @Override
    public void pack() {
        // System.out.println("\nПаралельна реалізація алгоритму пакування рюкзака");
        // Створення пулу потоків для виконання паралельних обчислень
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);

        long startTime = System.currentTimeMillis();
        int partCapacity = (capacity + 1) / numThreads; // часткова ємність для кожного потоку
        int[][] dt = new int[countItems + 1][capacity + 1]; // Масив для збереження результатів обчислень

        for (int i = 1; i <= countItems; i++) {                     // i - номер предмета
            CountDownLatch latch = new CountDownLatch(numThreads);  // Лічильник для синхронізації потоків
            for (int stage = 0; stage < numThreads; stage++) {      // цикл по потоках
                int itemIndex = i;                                  // Номер поточного предмета
                int partLeftIndex = stage * partCapacity;           // Ліва межа частини рюкзака для поточного потоку
                int partRightIndex;

                if (stage == numThreads - 1) {
                    partRightIndex = capacity;         // Права межа останньої частини рюкзака
                } else {
                    partRightIndex = partLeftIndex + partCapacity - 1; // Права межа для поточної частини рюкзака
                }

                pool.submit(() -> {                     // паралельне виконання
                    calculate(dt, latch, itemIndex, partLeftIndex, partRightIndex);
                });
            }
            try {
                latch.await();      // Очікування завершення всіх потоків
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();        // Завершення роботи пулу потоків після завершення всіх обчислень.

        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);  // Очікування завершення виконання всіх потоків
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        KnapSack knapSack = Helper.findSelectedItems(dt, itemsWarehouse, capacity); // відновити список вибраних предметів

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf(" Паралельна       %2d       %7d          %4d          %12d\n", numThreads, countItems, capacity, executionTime);
        //      knapSack.printResult(itemsWarehouse);   // друк змісту рюкзака
    }

    private void calculate(int[][] dt, CountDownLatch latch, int itemIndex, int partLeftIndex, int partRightIndex) {
        // обчислювальна робота виконується  паралельно у пулі потоків
        for (int j = partLeftIndex; j <= partRightIndex; j++) {         // j - місткості рюкзака
            if (itemsWarehouse.getWeight(itemIndex - 1) <= j) {     // Вага предмета менша за вміщувану в рюкзак
                int includePrice = itemsWarehouse.getPrice(itemIndex - 1) + dt[itemIndex - 1][j - itemsWarehouse.getWeight(itemIndex - 1)];
                int excludePrice = dt[itemIndex - 1][j];  // Вартість без включення поточного предмета
                if (includePrice > excludePrice) {
                    dt[itemIndex][j] = includePrice;       // покласти предмет в рюкзак
                } else {
                    dt[itemIndex][j] = excludePrice;       // не класти предмет
                }
            } else {
                dt[itemIndex][j] = dt[itemIndex - 1][j];   // Вага предмета більша за вміщувану місткість, пропускаємо предмет
            }
        }
        latch.countDown();  // Зменшити лічильник після завершення роботи кожного потоку
    }
}
