package org.example.models;

import com.github.javafaker.Faker;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ItemsWarehouse implements Serializable {
    private static ArrayList<Item> items;

    // Згенерувати новий набір
    public ItemsWarehouse(int countItems, int maxWeight, int maxPrice) {
        items = generate(countItems, maxWeight, maxPrice);
    }

    // Створити набір з завантаженого з файлу, де path - назва файлу
    public ItemsWarehouse(String filePath) {
        this.items = loadItems(filePath);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(int id) {
        return items.get(id);
    }

    public int getWeight(int id) {
        return items.get(id).getWeight();
    }

    public int getPrice(int id) {
        return items.get(id).getPrice();
    }

    public String getName(int id) {
        return items.get(id).getName();
    }

    private ArrayList<Item> generate(int count, int maxWeight, int maxPrice) {
        ArrayList<Item> array = new ArrayList<>();
        Faker faker = new Faker();
        Random randomWeight = new Random();
        Random randomPrice = new Random();

        // Генерація випадкових назв
        for (int i = 0; i < count; i++) {
            String name = faker.commerce().productName();
            int weight = randomWeight.nextInt(maxWeight) + 1;
            int price = randomPrice.nextInt(maxPrice) + 1;
            array.add(new Item(i, name, weight, price));
        }
        return array;
    }

    public void saveItems(String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(items);
            objectOut.close();
            fileOut.close();
            System.out.println("Об'єкти були серіалізовані та збережені у файлі " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> loadItems(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             BufferedInputStream bufferedIn = new BufferedInputStream(fileIn);
             ObjectInputStream objectIn = new ObjectInputStream(bufferedIn)) {
            ArrayList<Item> deserializedItems = (ArrayList<Item>) objectIn.readObject();
            return deserializedItems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printItems() {
        for (Item obj : items) {
            System.out.printf("%9d  %-45s %4d грн. %4d  кг\n", (obj.getId() + 1), obj.getName(), obj.getPrice(), obj.getWeight());
        }
    }

    public int[] getWeights() {
        int[] weights;
        weights = new int[items.size()];
        int i = 0;

        for (Item obj : items) {
            weights[i++] = obj.getWeight();
        }
        return weights;
    }

    public int[] getPrices() {
        int[] prices;
        prices = new int[items.size()];
        int i = 0;

        for (Item obj : items) {
            prices[i++] = obj.getPrice();
        }
        return prices;
    }

}

