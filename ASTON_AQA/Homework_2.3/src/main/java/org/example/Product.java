package org.example;

public class Product {
    String name;
    String dateOfProduction;
    String manufacturer;
    String country;
    int price;
    boolean reservationStatus;

    public Product() {
        this("Название не задано", "01.01.1970", "Производитель не задан", "Страна происхождения не задана", 0, false);
    }

    public Product(String name, String dateOfProduction, String manufacturer, String country, int price, boolean reservationStatus) {
        this.name = name;
        this.dateOfProduction = dateOfProduction;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.reservationStatus = reservationStatus;

    }

    void print() {
        System.out.printf("Название: " + name + "\nДата производства: " + dateOfProduction +
                "\nПроизводитель: " + manufacturer + "\nСтрана происхождения: " + country +
                "\nЦена: " + price + "\nСостояние бронирования покупателем: "  + reservationStatus);
    }
}