package org.example;

public class Cat extends Animal {

    private String color;
    private static int catCount;
    private boolean satiety = false;
    private int foodNeededForSatiety;

    public Cat(String name, String color, int foodNeededForSatiety) {
        super(name);
        this.color = color;
        if (foodNeededForSatiety < 0) {
            this.foodNeededForSatiety = 0;
        }
        else {
            this.foodNeededForSatiety = foodNeededForSatiety;
        }
        catCount++;
    }

    public String getColor() {
        return color;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean isSatiated() {
        return satiety;
    }


    public void catInfo() {
        String catName = this.getName();
        String catColor = this.getColor();
        System.out.println("Имя кота: " + catName + ", цвет: " + catColor);
    }

    @Override
    public void run(int a) {
        if (a > 200) {
            System.out.println("Кот пробежал: 200 м (максимальное расстояние)");
        } else if (a < 0) {
            System.out.println("Кот пробежал: 0 м");
        } else {
            System.out.println("Кот пробежал: " + a + " м");
        }
    }

    @Override
    public void swim(int a) {
        System.out.println("Коты не умеют плавать!");
    }

    public void eat(Bowl bowl) {
        if (this.isSatiated()) {
            System.out.println("Котик " + this.getName() + " уже cыт, поэтому не будет есть!");
        }
        else if (bowl.feedCat(this.foodNeededForSatiety)) {
            this.satiety = true;
            System.out.println("Котик " + this.getName() + " cыт!");
        }
        else {
            System.out.println("В миске слишком мало еды, " + this.getName() + " не сможет поесть достаточно, он не сыт.");
        }
    }

    public void satietyInfo() {
        if (this.satiety) {
            System.out.println("Кот " + this.getName() + " сыт.");
        }
        else {
            System.out.println("Кот " + this.getName() + " голоден.");
        }

    }
}
