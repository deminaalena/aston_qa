package org.example;

public class Dog extends Animal {

    private String color;
    private static int dogCount;

    public Dog(String name, String color) {
        super(name);
        this.color = color;
        dogCount++;
    }

    public String getColor() {
        return color;
    }

    public static int getDogCount() {
        return dogCount;
    }

    public void dogInfo() {
        String dogName = this.getName();
        String dogColor = this.getColor();
        System.out.println("Имя собаки: " + dogName + ", цвет: " + dogColor);
    }

    @Override
    public void run(int a) {
        if (a > 500) {
            System.out.println("Собака пробежала: 500 м (максимальное расстояние)");
        } else if (a < 0) {
            System.out.println("Собака пробежала: 0 м");
        } else {
            System.out.println("Собака пробежала: " + a + " м");
        }
    }

    @Override
    public void swim(int a) {
        if (a > 10) {
            System.out.println("Собака проплыла: 10 м (максимальное расстояние)");
        } else if (a < 0) {
            System.out.println("Собака проплыла: 0 м");
        } else {
            System.out.println("Собака проплыла: " + a + " м");
        }
    }
}
