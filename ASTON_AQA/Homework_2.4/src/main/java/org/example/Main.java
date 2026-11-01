package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1:");

        Cat catOne = new Cat("Барсик", "Белый", 15);
        catOne.catInfo();
        catOne.run(50);
        catOne.swim(200);

        Dog dogOne = new Dog("Бобик", "Чёрный");
        dogOne.dogInfo();
        dogOne.run(700);
        dogOne.swim(200);

        Cat catTwo = new Cat("Кексик", "Серый", 20);
        catTwo.catInfo();
        catTwo.run(300);
        catTwo.swim(0);

        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего котов: " + Cat.getCatCount());
        System.out.println("Всего собак: " + Dog.getDogCount() + "\n");

        Cat[] catsArray = new Cat[4];
        catsArray[0] = catOne;
        catsArray[1] = catTwo;
        catsArray[2] = new Cat("Чипсик", "Чёрно-белый", 30);
        catsArray[3] = new Cat("Рыжик", "Рыжий", 20);

        Bowl bowl = new Bowl(70);

        for (Cat cat : catsArray) {
            cat.eat(bowl);
        }

        for (Cat cat : catsArray) {
            cat.satietyInfo();
        }

        bowl.addFood(15);
        catsArray[3].eat(bowl);
        catsArray[3].eat(bowl);

        for (Cat cat : catsArray) {
            cat.satietyInfo();
        }

        System.out.println("________________________");
        System.out.println("Задание 2:");

        Circle circle = new Circle(5, "#F08080", "#DC143C");
        Rectangle rectangle = new Rectangle(10, 20, "#B0E0E6", "#1E90FF");
        Triangle triangle = new Triangle(3, 4, 5, "#FFDEAD", "#FFFFFF");

        System.out.println("Круг:");
        circle.shapeInfo();
        System.out.println("Прямоугольник:");
        rectangle.shapeInfo();
        System.out.println("Треугольник:");
        triangle.shapeInfo();
    }
}