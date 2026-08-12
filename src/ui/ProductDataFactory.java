package ui;

import java.util.ArrayList;
import java.util.List;

public class ProductDataFactory {
    public static List<ProductCard> getProductsFor(String category) {
        List<ProductCard> products = new ArrayList<>();
        if (category.equals("Смартфоны")) {
            products.add(new ProductCard("Iphone 16 Pro Max", "145 000 р"));
            products.add(new ProductCard("Xiomi 15 Pro", "75 000 р"));
            products.add(new ProductCard("Iphone 11", "30 000 р"));
            products.add(new ProductCard("Samsung S25 Ultra", "75 000 р"));
            products.add(new ProductCard("Xiaomi Redmi 15", "16 999 р"));
        }
        if (category.equals("Аксессуары")) {
            products.add(new ProductCard("Чехол на Iphone 16 Pro Max", "200 р"));
        }
        if (category.equals("Смарт-Часы")) {
            products.add(new ProductCard("Apple Watch SE", "25 000 р"));
        }

        if (category.equals("Телевизоры")) {
            products.add(new ProductCard("SAMSUNG 55", "169 999"));
        }
        if (category.equals("Консоли")) {
            products.add(new ProductCard("XBOX Series S", "30 000 р"));
        }
        if (category.equals("Аудио системы")) {
            products.add(new ProductCard("Яндекс станция миди", "16 299 р"));
        }

        if (category.equals("ПК")) {
            products.add(new ProductCard("ARDOR Gaming Rage H461", "121 999 р"));
        }
        if (category.equals("Ноутбуки")) {
            products.add(new ProductCard("HUAWEI MateBook D 16", "52 299 р"));
            products.add(new ProductCard("HOROR MagikBook X16 AMD", "57 299 р"));
        }
        if (category.equals("Периферия")) {
            products.add(new ProductCard("Logitec 435", "5000 р"));
            products.add(new ProductCard("Red Square TKL", "6000 р"));
        }

        if (category.equals("Видеокарты")) {
            products.add(new ProductCard("RTX 5060 Dual", "43 299 р"));
            products.add(new ProductCard("RTX 4090", "160 000 р"));
            products.add(new ProductCard("AMD Radeon RX 9060 XT Reaper", "48 299 р"));
        }
        if (category.equals("Процессоры")) {
            products.add(new ProductCard("Ryzen 5 5600", "10 200 р"));
            products.add(new ProductCard("Ryzen 7 5700X", "15 799 р"));
        }

        return products;
    }
}