package org.example;

import org.example.models.ItemsWarehouse;
import org.example.parallel.ParallelDynamic;
import org.example.serial.Dynamic;

public class Main {
    public static void main(String[] args) {
        // ItemsWarehouse itemsWarehouse = new ItemsWarehouse(17, 200, 500);
        //   itemsWarehouse.saveItems("src/main/resources/items-17-200-500.dat");

        ItemsWarehouse itemsWarehouse;

        int capacity = 15000;

        Dynamic dynamic;
        ParallelDynamic parallelDynamic;

        itemsWarehouse = new ItemsWarehouse("src/main/resources/items-17-200-500.dat");

        //    itemsWarehouse.printItems();

        System.out.println("Реалізація     Threads        Items         Capacity               T,мс");

        dynamic = new Dynamic(itemsWarehouse, capacity);
        dynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 2);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 4);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 8);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 12);
        parallelDynamic.pack();

        itemsWarehouse = new ItemsWarehouse("src/main/resources/items-170-200-500.dat");
        dynamic = new Dynamic(itemsWarehouse, capacity);
        dynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 2);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 4);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 8);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 12);
        parallelDynamic.pack();


        itemsWarehouse = new ItemsWarehouse("src/main/resources/items-1700-200-500.dat");
        dynamic = new Dynamic(itemsWarehouse, capacity);
        dynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 2);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 4);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 8);
        parallelDynamic.pack();

        parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 12);
        parallelDynamic.pack();

//        itemsWarehouse = new ItemsWarehouse("d:\\items12.dat");
//        System.out.println("Вихідні дані:");
//        itemsWarehouse.printItems();
//        Bruteforce bruteforce = new Bruteforce(itemsWarehouse, capacity);
//        bruteforce.pack();


        // підготовка наборів вхідних даних на диску
        //   itemsWarehouse = new ItemsWarehouse(10000000,40,100);
        //      itemsWarehouse.saveItems("d:\\items-10mln-40-100.dat");

        // itemsWarehouse = new ItemsWarehouse("d:\\items-100000-40-100.dat");
        //itemsWarehouse = new ItemsWarehouse("d:\\items-10mln-40-100.dat");

        // System.out.println("Реалізація     Threads        Items         Capacity               T,мс");

        // *********************************************************************************

        // Dynamic dynamic = new Dynamic(itemsWarehouse, capacity);
        // dynamic.pack();

        // *********************************************************************************

        // ParallelDynamic parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 4);
        // parallelDynamic.pack();

        // parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 4);
        // parallelDynamic.pack();

        // parallelDynamic = new ParallelDynamic(itemsWarehouse, capacity, 16);
        // parallelDynamic.pack();


//        DynamicParallel dynamicParallel = new DynamicParallel(itemsWarehouse, capacity);
//        dynamicParallel.pack();


        // ********************


        //
//        Dynamic dynamic = new Dynamic(itemsWarehouse, capacity);
//        dynamic.pack();
//

//
//        Bruteforce bruteforce = new Bruteforce(itemsWarehouse, capacity);
//        bruteforce.pack();

//
//        ItemsWarehouse itemsWarehouse;


//        int capacity = 100000;
//        int maxWeight = 200;
//        int maxPrice = 20000;
//        itemsWarehouse = new ItemsWarehouse(10,maxWeight,maxPrice);
//        itemsWarehouse.saveItems("d:\\items-10-40-100.dat");
//        itemsWarehouse = new ItemsWarehouse(100,maxWeight,maxPrice);
//        itemsWarehouse.saveItems("d:\\items-100-200-20000.dat");
//        itemsWarehouse = new ItemsWarehouse(1000,maxWeight,maxPrice);
//        itemsWarehouse.saveItems("d:\\items-1000-200-20000.dat");
//        itemsWarehouse = new ItemsWarehouse(10000,maxWeight,maxPrice);
//        itemsWarehouse.saveItems("d:\\items-10000-200-20000.dat");
//        itemsWarehouse = new ItemsWarehouse(100000,maxWeight,maxPrice);
//        itemsWarehouse.saveItems("d:\\items-100000-200-20000.dat");
//        itemsWarehouse = new ItemsWarehouse(1000000,maxWeight,maxPrice);
//        itemsWarehouse.saveItems("d:\\items-1000000-200-20000.dat");

//        itemsWarehouse = new ItemsWarehouse("d:\\items-10-40-100.dat");
//        dynamic = new Dynamic(itemsWarehouse, capacity);
//        dynamic.pack();


//        itemsWarehouse = new ItemsWarehouse("d:\\items-100-200-20000.dat");
//        dynamic = new Dynamic(itemsWarehouse, capacity);
//        dynamic.pack();
//
//        itemsWarehouse = new ItemsWarehouse("d:\\items-1000-200-20000.dat");
//        dynamic = new Dynamic(itemsWarehouse, capacity);
//        dynamic.pack();
//
//        itemsWarehouse = new ItemsWarehouse("d:\\items-10000-200-20000.dat");
//        dynamic = new Dynamic(itemsWarehouse, capacity);
//        dynamic.pack();
//
//        itemsWarehouse = new ItemsWarehouse("d:\\items-100000-200-20000.dat");
//        dynamic = new Dynamic(itemsWarehouse, capacity);
//        dynamic.pack();
//


    }
}