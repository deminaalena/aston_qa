package org.example;

public interface Shape {
    double calculateArea();
    double calculatePerimeter();
    String getFillColor();
    String getBorderColor();

    default double calculatePerimeter(double radius) {
        return 2 * Math.PI * radius;
    }

    default double calculatePerimeter(double width, double height) {
        return 2 * (width + height);
    }

    default double calculatePerimeter(double a, double b, double c) {
        return a + b + c;
    }

    default void shapeInfo() {
        System.out.println("Характеристики фигуры: [" +
                calculatePerimeter() + ", " +
                calculateArea() + ", " +
                getFillColor() + ", " +
                getBorderColor() + "]");
    }
}
