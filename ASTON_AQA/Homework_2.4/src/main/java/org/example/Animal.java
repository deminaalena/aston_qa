package org.example;

public abstract class Animal {

    private String name;
    private static int animalCount;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public String getName() {
        return name;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public void animalInfo() {
        System.out.println("Животное: " + name);
    }

    public abstract void run(int a);

    public abstract void swim(int a);
}
