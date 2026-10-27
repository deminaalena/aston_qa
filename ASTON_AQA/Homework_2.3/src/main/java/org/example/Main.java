package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1:\n");

        Product emptyProduct = new Product();
        emptyProduct.print();

        System.out.println("\n");

        Product fullProduct = new Product("Смартфон", "10.09.2026",
                "Xiaomi", "Китай", 40000, true);
        fullProduct.print();

        System.out.println("\n____________________");
        System.out.println("Задание 2:\n");

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);

        productsArray[1] = new Product("iPhone 17 Pro", "09.09.2025",
                "Apple Inc.", "USA", 1099, false);

        productsArray[2] = new Product("Xiaomi 15 Ultra", "27.02.2025",
                "Xiaomi Corporation", "China", 899, true);

        productsArray[3] = new Product("PlayStation 5 Slim", "10.11.2023",
                "Sony Interactive Entertainment", "Japan", 449, false);

        productsArray[4] = new Product("Nintendo Switch 2", "05.06.2025",
                "Nintendo Co., Ltd.", "Japan", 449, true);

        for (Product product : productsArray) {
            product.print();
            System.out.println("\n");
        }

        System.out.println("\n____________________");
        System.out.println("Задание 3:\n");

        Park park = new Park("Джавариум");
        park.printPark();

        Park.Ride[] ridesArray = new Park.Ride[5];

        ridesArray[0] = park.new Ride("Колесо циклов", "10:00–22:00", 500);
        ridesArray[1] = park.new Ride("Американские массивы", "11:00–21:00", 700);
        ridesArray[2] = park.new Ride("Интерфейс страха", "12:00–23:00", 400);
        ridesArray[3] = park.new Ride("Оверрайд-призрак", "12:00–23:00", 500);
        ridesArray[4] = park.new Ride("Дом с сущностями", "10:00–22:00", 450);

        for (Park.Ride ride : ridesArray) {
            ride.printRide();
        }
    }
}