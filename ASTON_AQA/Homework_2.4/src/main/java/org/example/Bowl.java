package org.example;

public class Bowl {

    private int currentFood;

    public Bowl (int currentFood) {
        this.currentFood = Math.max(currentFood, 0);
    }

    public void addFood(int amountOfFood) {
        if (amountOfFood > 0) {
            System.out.println("Добавлено корма: " + amountOfFood);
            System.out.println("Корма всего: " + (this.currentFood += amountOfFood));
        } else {
            System.out.println("Ошибка, введите положительное количество корма.");
            System.out.println("Корма всего: " + this.currentFood);
        }
    }

    public boolean feedCat(int amountOfFood) {
        if (amountOfFood < 0) {
            System.out.println("Ошибка, введите положительное количество корма.");
            return false;
        }
        if (amountOfFood <= this.currentFood) {
            System.out.println("Количество корма до кормления: " + this.currentFood);
            this.currentFood -= amountOfFood;
            System.out.println("Количество корма после кормления: " + this.currentFood);
            return true;
        }
        else {
            return false;
        }
    }
}
